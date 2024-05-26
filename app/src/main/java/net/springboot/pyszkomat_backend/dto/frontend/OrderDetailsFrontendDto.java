package net.springboot.pyszkomat_backend.dto.frontend;

import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.model.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsFrontendDto {

    public Long orderId;
    public LocalDateTime orderTime;
    public LocalDateTime deliveryTime;
    public LocalDateTime pickUpTime;
    public OrderStatus status;
    public boolean isHeated;
    public List<OrdersDetailsOrderItemFrontendDto> orderItems;
    public OrdersDetailsParcelMachineFrontendDto parcelMachine;

    public OrderDetailsFrontendDto() {
    }

    public OrderDetailsFrontendDto(Order order) {
        orderId = order.id;
        orderTime = order.orderTime;
        deliveryTime = order.deliveryTime;
        pickUpTime = order.pickUpTime;
        status = order.status;
        isHeated = order.isHeated;

        orderItems = new ArrayList<>();
        for (OrderItem orderItem : order.orderItems) {
            orderItems.add(new OrdersDetailsOrderItemFrontendDto(orderItem.menuItem.name, orderItem.quantity));
        }

        parcelMachine = new OrdersDetailsParcelMachineFrontendDto(
                order.locker.parcelMachine.id,
                order.locker.parcelMachine.address
        );
    }
}