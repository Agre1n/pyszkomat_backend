package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.OrderCrudDto;
import net.springboot.pyszkomat_backend.dto.frontend.*;
import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.*;
import net.springboot.pyszkomat_backend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/frontend")
public class FrontendController {

    private final CustomerService customerService;
    private final MenuItemService menuItemService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;
    private final ParcelMachineService parcelMachineService;
    private final RestaurantService restaurantService;

    public FrontendController(
            CustomerService customerService,
            LockerService lockerService,
            MenuItemService menuItemService,
            OrderItemService orderItemService,
            OrderService orderService,
            ParcelMachineService parcelMachineService,
            RestaurantService restaurantService
    ) {
        this.customerService = customerService;
        this.menuItemService = menuItemService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.parcelMachineService = parcelMachineService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/parcel_machines")
    public ResponseEntity<List<ParcelMachineFrontendDto>> getAllParcelMachines() {
        List<ParcelMachineFrontendDto> parcelMachines = new ArrayList<>();

        for (ParcelMachine parcelMachine : parcelMachineService.getParcelMachines()) {
            parcelMachines.add(new ParcelMachineFrontendDto(parcelMachine));
        }

        return ResponseEntity.ok(parcelMachines);
    }

    @GetMapping("/parcel_machine/restaurants/{parcelMachineId}")
    public ResponseEntity<List<RestaurantFrontendDto>> getParcelMachineRestaurants(@PathVariable String parcelMachineId) {
        List<RestaurantFrontendDto> restaurants = new ArrayList<>();

        for (Restaurant restaurant : parcelMachineService.getParcelMachine(parcelMachineId).restaurants) {
            restaurants.add(new RestaurantFrontendDto(restaurant));
        }

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/restaurant/menu_items/{restaurantId}")
    public ResponseEntity<List<MenuItemFrontendDto>> getRestaurantMenuItems(@PathVariable Long restaurantId) {
        List<MenuItemFrontendDto> menuItems = new ArrayList<>();

        for (MenuItem menuItem : restaurantService.getRestaurant(restaurantId).menuItems) {
            menuItems.add(new MenuItemFrontendDto(menuItem));
        }

        return ResponseEntity.ok(menuItems);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderCrudDto> createOrder(@RequestBody NewOrderFrontendDto orderDto) {
        Random random = new Random();
        int randomDeliveryTime = random.nextInt(30) + 60;
        int randomPickUpTime = random.nextInt(30) + 60;

        Order newOrder = orderService.addOrder(
                new Order(
                        0L,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(randomDeliveryTime),
                        LocalDateTime.now().plusMinutes(randomDeliveryTime + randomPickUpTime),
                        OrderStatus.PREPARED,
                        false,
                        customerService.getCustomer(orderDto.customerId),
                        parcelMachineService.getParcelMachine(orderDto.parcelMachineId).lockers.getFirst(),
                        new ArrayList<>()
                )
        );

        for (NewOrderItemFrontendDto orderItemDTO : orderDto.orderItems) {
            orderItemService.addOrderItem(new OrderItem(
                            0L,
                            orderItemDTO.quantity,
                            menuItemService.getMenuItem(orderItemDTO.menuItemId),
                            newOrder
                    )
            );
        }

        return ResponseEntity.ok(new OrderCrudDto(newOrder));
    }

    @GetMapping("/customers/orders/{customerId}")
    public ResponseEntity<List<OrderFrontendDto>> getCustomerActiveOrders(@PathVariable Long customerId) {
        List<OrderFrontendDto> orders = new ArrayList<>();

        for (Order order : customerService.getCustomer(customerId).orders) {
            if (order.status == OrderStatus.DELIVERED)
                orders.add(new OrderFrontendDto(order));
            if (order.status == OrderStatus.PREPARED)
                orders.add(new OrderFrontendDto(order));
            if (order.status == OrderStatus.READY_FOR_PICKUP)
                orders.add(new OrderFrontendDto(order));
        }

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderDetailsFrontendDto> getOrderDetails(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);
        OrderDetailsFrontendDto orderDto = new OrderDetailsFrontendDto(order);

        return ResponseEntity.ok(orderDto);
    }

    @PutMapping("/orders/heating/{orderId}")
    public ResponseEntity<?> updateOrderHeating(@PathVariable Long orderId, @RequestBody boolean heating) {
        Order order = orderService.getOrder(orderId);

        if (order.status != OrderStatus.READY_FOR_PICKUP)
            return ResponseEntity.status(400).body("Order is not ready for pick up");

        order.isHeated = heating;

        return ResponseEntity.ok(new OrderCrudDto(orderService.updateOrder(orderId, order)));
    }

    @GetMapping("/orders/pick_up/{orderId}")
    public ResponseEntity<?> updateOrderPickUp(@PathVariable Long orderId) {
        Order order = orderService.getOrder(orderId);

        if (order.status != OrderStatus.READY_FOR_PICKUP)
            return ResponseEntity.status(400).body("Order is not ready for pick up");

        order.status = OrderStatus.RECEIVED;

        return ResponseEntity.ok(new OrderCrudDto(orderService.updateOrder(orderId, order)));
    }
}
