package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.model.OrderItem;
import net.springboot.pyszkomat_backend.service.CustomerService;
import net.springboot.pyszkomat_backend.service.LockerService;
import net.springboot.pyszkomat_backend.service.OrderItemService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderCrudDto {

    public Long id;
    public LocalDateTime orderTime;
    public LocalDateTime deliveryTime;
    public LocalDateTime pickUpTime;
    public OrderStatus status;
    public boolean isHeated;
    public Long customerId;
    public Long lockerId;
    public List<Long> orderItemsIds;

    public OrderCrudDto() {
    }

    public OrderCrudDto(Order order) {
        id = order.id;
        orderTime = order.orderTime;
        deliveryTime = order.deliveryTime;
        pickUpTime = order.pickUpTime;
        status = order.status;
        isHeated = order.isHeated;
        customerId = order.customer.id;
        lockerId = order.locker.id;

        orderItemsIds = new ArrayList<>();
        for (OrderItem orderItem : order.orderItems) {
            orderItemsIds.add(orderItem.id);
        }
    }

    public Order toOrder(
            CustomerService customerService,
            LockerService lockerService,
            OrderItemService orderItemService
    ) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Long orderItemId : orderItemsIds) {
            orderItems.add(orderItemService.getOrderItem(orderItemId));
        }

        return new Order(
                this.id,
                this.orderTime,
                this.deliveryTime,
                this.pickUpTime,
                this.status,
                this.isHeated,
                customerService.getCustomer(this.customerId),
                lockerService.getLocker(this.lockerId),
                orderItems
        );
    }
}
