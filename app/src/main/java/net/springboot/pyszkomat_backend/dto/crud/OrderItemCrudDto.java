package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.service.MenuItemService;
import net.springboot.pyszkomat_backend.service.OrderService;

public class OrderItemCrudDto {

    public Long id;
    public int quantity;
    public Long menuItemId;
    public Long orderId;

    public OrderItemCrudDto(OrderItem orderItem) {
        this.id = orderItem.id;
        this.quantity = orderItem.quantity;
        this.menuItemId = orderItem.menuItem.id;
        this.orderId = orderItem.order.id;
    }

    public OrderItem toOrderItem(MenuItemService menuItemService, OrderService orderService) {
        return new OrderItem(
                this.id,
                this.quantity,
                menuItemService.getMenuItem(this.menuItemId),
                orderService.getOrder(this.orderId)
        );
    }
}
