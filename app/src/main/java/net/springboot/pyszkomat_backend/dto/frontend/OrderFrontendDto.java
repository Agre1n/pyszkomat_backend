package net.springboot.pyszkomat_backend.dto.frontend;

import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.Order;

import java.time.LocalDateTime;

public class OrderFrontendDto {

    public Long orderId;
    public LocalDateTime time;
    public OrderStatus status;
    public String restaurantName;
    public String parcelMachineId;

    public OrderFrontendDto() {
    }

    public OrderFrontendDto(Order order) {
        orderId = order.id;

        switch (order.status) {
            case OrderStatus.PREPARED, OrderStatus.DELIVERED:
                time = order.deliveryTime;
                break;
            case OrderStatus.READY_FOR_PICKUP:
                time = order.pickUpTime;
                break;
            default:
                time = order.orderTime;
                break;
        }

        status = order.status;
        restaurantName = order.orderItems.getFirst().menuItem.restaurant.name;
        parcelMachineId = order.locker.parcelMachine.id;
    }
}
