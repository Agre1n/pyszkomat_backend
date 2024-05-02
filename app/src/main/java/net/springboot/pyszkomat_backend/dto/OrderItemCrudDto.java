package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.repository.MenuItemRepository;
import net.springboot.pyszkomat_backend.repository.OrderRepository;

public class OrderItemCrudDto {

    private Long id;
    private int quantity;
    private Long menuItemId;
    private Long orderId;

    public OrderItemCrudDto() {
    }

    public OrderItemCrudDto(OrderItem orderItem) {
        this.id = orderItem.getId();
        this.quantity = orderItem.getQuantity();
        this.menuItemId = orderItem.getMenuItem().getId();
        this.orderId = orderItem.getOrder().getId();
    }

    public OrderItem toOrderItem(MenuItemRepository menuItemRepository, OrderRepository orderRepository) {
        return new OrderItem(
                this.id,
                this.quantity,
                menuItemRepository.findById(this.menuItemId).orElse(null),
                orderRepository.findById(this.orderId).orElse(null)
        );
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
