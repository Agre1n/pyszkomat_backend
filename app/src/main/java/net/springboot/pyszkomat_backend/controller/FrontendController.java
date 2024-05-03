package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.*;
import net.springboot.pyszkomat_backend.dto.frontend.OrderFrontendDto;
import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.*;
import net.springboot.pyszkomat_backend.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/frontend")
public class FrontendController {

    private final CustomerService customerService;
    private final LockerService lockerService;
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
        this.lockerService = lockerService;
        this.menuItemService = menuItemService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
        this.parcelMachineService = parcelMachineService;
        this.restaurantService = restaurantService;
    }

    @GetMapping("/parcel_machines")
    public ResponseEntity<List<ParcelMachineCrudDto>> getAllParcelMachines() {
        List<ParcelMachineCrudDto> parcelMachines = new ArrayList<>();

        for (ParcelMachine parcelMachine : parcelMachineService.getParcelMachines()) {
            parcelMachines.add(new ParcelMachineCrudDto(parcelMachine));
        }

        return ResponseEntity.ok(parcelMachines);
    }

    @GetMapping("/parcel_machine/restaurants/{parcelMachineId}")
    public ResponseEntity<List<RestaurantCrudDto>> getParcelMachineRestaurants(@PathVariable String parcelMachineId) {
        List<RestaurantCrudDto> restaurants = new ArrayList<>();

        for (Restaurant restaurant : parcelMachineService.getParcelMachine(parcelMachineId).restaurants) {
            restaurants.add(new RestaurantCrudDto(restaurant));
        }

        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/restaurant/menu_items/{restaurantId}")
    public ResponseEntity<List<MenuItemCrudDto>> getRestaurantMenuItems(@PathVariable Long restaurantId) {
        List<MenuItemCrudDto> menuItems = new ArrayList<>();

        for (MenuItem menuItem : restaurantService.getRestaurant(restaurantId).menuItems) {
            menuItems.add(new MenuItemCrudDto(menuItem));
        }

        return ResponseEntity.ok(menuItems);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderCrudDto> createOrder(@RequestBody OrderFrontendDto orderDto) {
        orderDto.order.orderItemsIds = new ArrayList<>();

        Order newOrder = orderService.addOrder(
                orderDto.order.toOrder(customerService, lockerService, orderItemService)
        );

        for (OrderItemCrudDto orderItemDTO : orderDto.orderItems) {
            orderItemDTO.orderId = newOrder.id;
            orderItemService.addOrderItem(orderItemDTO.toOrderItem(menuItemService, orderService));
        }

        return ResponseEntity.ok(new OrderCrudDto(newOrder));
    }

    @GetMapping("/customers/orders/{customerId}")
    public ResponseEntity<List<OrderCrudDto>> getCustomerActiveOrders(@PathVariable Long customerId) {
        List<OrderCrudDto> orders = new ArrayList<>();

        for (Order order : customerService.getCustomer(customerId).orders) {
            if (order.status == OrderStatus.DELIVERED)
                orders.add(new OrderCrudDto(order));
            if (order.status == OrderStatus.PREPARED)
                orders.add(new OrderCrudDto(order));
            if (order.status == OrderStatus.READY_FOR_PICKUP)
                orders.add(new OrderCrudDto(order));
        }

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<OrderFrontendDto> getOrderDetails(@PathVariable Long orderId) {
        OrderFrontendDto orderDto = new OrderFrontendDto();
        Order order = orderService.getOrder(orderId);

        orderDto.order = new OrderCrudDto(order);
        orderDto.orderItems = new ArrayList<>();

        for (OrderItem orderItem : order.orderItems) {
            orderDto.orderItems.add(new OrderItemCrudDto(orderItem));
        }

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
