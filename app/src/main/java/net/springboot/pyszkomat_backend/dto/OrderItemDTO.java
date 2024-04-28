package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.model.OrderItem;

public class OrderItemDTO {

    private Long id;
    private int quantity;
    private float totalPrice;
    private Long menuItemId;
    private Long orderId;

    public OrderItemDTO() {
    }

    public OrderItemDTO(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.totalPrice = orderItem.getTotalPrice();
        this.menuItemId = orderItem.getMenuItem().getId();
        this.orderId = orderItem.getOrder().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
