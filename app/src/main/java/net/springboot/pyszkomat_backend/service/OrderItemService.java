package net.springboot.pyszkomat_backend.service;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.repository.OrderItemRepository;

public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem getOrderItem(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem with id : " + id + " not found"));
    }

    public Iterable<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem addOrderItem(@Valid OrderItem orderItem) {
        orderItem.id = null;
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, @Valid OrderItem orderItem) {
        OrderItem _ = this.getOrderItem(id);

        orderItem.id = id;
        return orderItemRepository.save(orderItem);
    }

    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}
