package net.springboot.pyszkomat_backend.controller;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id :" + id + " not found"));

        return ResponseEntity.ok(order);
    }

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
            @PathVariable Long id,
            @Valid @RequestBody Order orderDetails
    ) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id :" + id + " not found"));

        order.setOrderTime(orderDetails.getOrderTime());
        order.setDeliveryTime(orderDetails.getDeliveryTime());
        order.setPickUpTime(orderDetails.getPickUpTime());
        order.setStatus(orderDetails.getStatus());
        order.setHeated(orderDetails.getIsHeated());
        order.setCustomer(orderDetails.getCustomer());
        order.setLocker(orderDetails.getLocker());

        Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id :" + id + " not found"));

        orderRepository.delete(order);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
