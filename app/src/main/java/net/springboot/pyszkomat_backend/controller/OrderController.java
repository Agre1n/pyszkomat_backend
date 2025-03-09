package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.OrderCrudDto;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.service.CustomerService;
import net.springboot.pyszkomat_backend.service.LockerService;
import net.springboot.pyszkomat_backend.service.OrderItemService;
import net.springboot.pyszkomat_backend.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final CustomerService customerService;
    private final LockerService lockerService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    public OrderController(
            CustomerService customerService,
            LockerService lockerService,
            OrderItemService orderItemService,
            OrderService orderService
    ) {
        this.customerService = customerService;
        this.lockerService = lockerService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderCrudDto>> getAllOrders() {
        List<OrderCrudDto> orders = new ArrayList<>();

        for (Order order : orderService.getOrders()) {
            orders.add(new OrderCrudDto(order));
        }

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderCrudDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(new OrderCrudDto(orderService.getOrder(id)));
    }

    @PostMapping
    public ResponseEntity<OrderCrudDto> createOrder(@RequestBody OrderCrudDto orderDTO) {
        orderDTO.orderItemsIds = new ArrayList<>();

        Order newOrder = orderService.addOrder(
                orderDTO.toOrder(customerService, lockerService, orderItemService)
        );

        return ResponseEntity.ok(new OrderCrudDto(newOrder));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderCrudDto> updateOrder(
            @PathVariable Long id,
            @RequestBody OrderCrudDto orderDTO
    ) {
        orderDTO.orderItemsIds = new ArrayList<>();

        Order updatedOrder = orderService.updateOrder(
                id, orderDTO.toOrder(customerService, lockerService, orderItemService)
        );

        return ResponseEntity.ok(new OrderCrudDto(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
