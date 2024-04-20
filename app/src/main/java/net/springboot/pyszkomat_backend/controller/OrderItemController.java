package net.springboot.pyszkomat_backend.controller;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.repository.OrderItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/order_items")
public class OrderItemController {

    private final OrderItemRepository orderItemRepository;

    public OrderItemController(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem with id :" + id + " not found"));

        return ResponseEntity.ok(orderItem);
    }

    @PostMapping
    public OrderItem createOrderItem(@Valid @RequestBody OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable Long id,
            @Valid @RequestBody OrderItem orderItemDetails
    ) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem with id :" + id + " not found"));

        orderItem.setQuantity(orderItemDetails.getQuantity());
        orderItem.setMenuItem(orderItemDetails.getMenuItem());
        orderItem.setOrder(orderItemDetails.getOrder());

        OrderItem updatedOrderItem = orderItemRepository.save(orderItem);
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrderItem(@PathVariable Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem with id :" + id + " not found"));

        orderItemRepository.delete(orderItem);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
