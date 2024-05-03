package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.service.MenuItemService;
import net.springboot.pyszkomat_backend.service.OrderService;

public class OrderItemCrudDto {

    public Long id;
    public int quantity;
    public Long menuItemId;
    public Long orderId;

    public OrderItemCrudDto() {
    }

    public OrderItemCrudDto(OrderItem orderItem) {
        id = orderItem.id;
        quantity = orderItem.quantity;
        menuItemId = orderItem.menuItem.id;
        orderId = orderItem.order.id;
    }

    public OrderItem toOrderItem(MenuItemService menuItemService, OrderService orderService) {
        return new OrderItem(
                id,
                quantity,
                menuItemService.getMenuItem(menuItemId),
                orderService.getOrder(orderId)
        );
    }
}
