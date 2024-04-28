package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.model.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    private Long id;
    private LocalDateTime orderTime;
    private LocalDateTime deliveryTime;
    private LocalDateTime pickUpTime;
    private OrderStatus status;
    private boolean isHeated;
    private float totalPrice;
    private Long customerId;
    private String parcelMachineId;
    private List<Long> orderItemsIds;

    public OrderDTO() {
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderTime = order.getOrderTime();
        this.deliveryTime = order.getDeliveryTime();
        this.pickUpTime = order.getPickUpTime();
        this.status = order.getStatus();
        this.isHeated = order.getIsHeated();
        this.totalPrice = order.getTotalPrice();
        this.customerId = order.getCustomer().getId();
        this.parcelMachineId = order.getLocker().getParcelMachine().getId();
        this.orderItemsIds = new ArrayList<>();

        for (OrderItem orderItem : order.getOrderItems()) {
            this.orderItemsIds.add(orderItem.getId());
        }
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

    public void setIsHeated(boolean isHeated) {
        this.isHeated = isHeated;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getParcelMachineId() {
        return parcelMachineId;
    }

    public void setParcelMachineId(String parcelMachineId) {
        this.parcelMachineId = parcelMachineId;
    }

    public List<Long> getOrderItemsIds() {
        return orderItemsIds;
    }

    public void setOrderItemsIds(List<Long> orderItemsIds) {
        this.orderItemsIds = orderItemsIds;
    }
}
