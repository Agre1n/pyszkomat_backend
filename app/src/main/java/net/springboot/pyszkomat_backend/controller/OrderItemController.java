package net.springboot.pyszkomat_backend.controller;

import net.springboot.pyszkomat_backend.dto.crud.OrderItemCrudDto;
import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.service.MenuItemService;
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
@RequestMapping("/api/order_items")
public class OrderItemController {

    private final MenuItemService menuItemService;
    private final OrderItemService orderItemService;
    private final OrderService orderService;

    public OrderItemController(
            MenuItemService menuItemService,
            OrderItemService orderItemService,
            OrderService orderService
    ) {
        this.menuItemService = menuItemService;
        this.orderItemService = orderItemService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemCrudDto>> getAllOrderItems() {
        List<OrderItemCrudDto> orderItems = new ArrayList<>();

        for (OrderItem orderItem : orderItemService.getOrderItems()) {
            orderItems.add(new OrderItemCrudDto(orderItem));
        }

        return ResponseEntity.ok(orderItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemCrudDto> getOrderItemById(@PathVariable Long id) {
        return ResponseEntity.ok(new OrderItemCrudDto(orderItemService.getOrderItem(id)));
    }

    @PostMapping
    public ResponseEntity<OrderItemCrudDto> createOrderItem(@RequestBody OrderItemCrudDto orderItemDTO) {
        OrderItem newOrderItem = orderItemService.addOrderItem(
                orderItemDTO.toOrderItem(menuItemService, orderService)
        );

        return ResponseEntity.ok(new OrderItemCrudDto(newOrderItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemCrudDto> updateOrderItem(
            @PathVariable Long id,
            @RequestBody OrderItemCrudDto orderItemDTO) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(
                id, orderItemDTO.toOrderItem(menuItemService, orderService)
        );

        return ResponseEntity.ok(new OrderItemCrudDto(updatedOrderItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
