package net.springboot.pyszkomat_backend.service;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order with id : " + id + " not found"));
    }

    public Iterable<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(@Valid Order order) {
        order.id = null;
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, @Valid Order order) {
        Order _ = getOrder(id);

        order.id = id;
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        Order _ = getOrder(id);

        orderRepository.deleteById(id);
    }

    public boolean isEmpty() {
        return orderRepository.count() == 0;
    }
}
