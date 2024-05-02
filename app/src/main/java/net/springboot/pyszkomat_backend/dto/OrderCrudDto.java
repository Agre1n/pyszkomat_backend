package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.repository.CustomerRepository;
import net.springboot.pyszkomat_backend.repository.LockerRepository;
import net.springboot.pyszkomat_backend.repository.OrderItemRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderCrudDto {

    private Long id;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    private LocalDateTime pickUpTime;
    private OrderStatus status;
    private boolean isHeated;
    private Long customerId;
    private Long lockerId;
    private List<Long> orderItemsIds;

    public OrderCrudDto() {
    }

    public OrderCrudDto(Order order) {
        this.id = order.getId();
        this.orderTime = order.getOrderTime();
        this.deliveryTime = order.getDeliveryTime();
        this.pickUpTime = order.getPickUpTime();
        this.status = order.getStatus();
        this.isHeated = order.getIsHeated();
        this.customerId = order.getCustomer().getId();
        this.lockerId = order.getLocker().getId();

        this.orderItemsIds = new ArrayList<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            this.orderItemsIds.add(orderItem.getId());
        }
    }

    public Order toOrder(
            CustomerRepository customerRepository,
            LockerRepository lockerRepository,
            OrderItemRepository orderItemRepository
    ) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Long orderItemId : this.orderItemsIds) {
            orderItemRepository.findById(orderItemId).ifPresent(orderItems::add);
        }

        return new Order(
                this.id,
                this.orderTime,
                this.deliveryTime,
                this.pickUpTime,
                this.status,
                this.isHeated,
                customerRepository.findById(this.customerId).orElse(null),
                lockerRepository.findById(this.lockerId).orElse(null),
                orderItems
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean getIsHeated() {
        return isHeated;
    }

    public void setIsHeated(boolean heated) {
        isHeated = heated;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getLockerId() {
        return lockerId;
    }

    public void setLockerId(Long lockerId) {
        this.lockerId = lockerId;
    }

    public List<Long> getOrderItemsIds() {
        return orderItemsIds;
    }

    public void setOrderItemsIds(List<Long> orderItemsIds) {
        this.orderItemsIds = orderItemsIds;
    }
}
