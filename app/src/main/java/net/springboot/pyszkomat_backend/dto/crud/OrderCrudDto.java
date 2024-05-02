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

    public OrderCrudDto(Order order) {
        this.id = order.id;
        this.orderTime = order.orderTime;
        this.deliveryTime = order.deliveryTime;
        this.pickUpTime = order.pickUpTime;
        this.status = order.status;
        this.isHeated = order.isHeated;
        this.customerId = order.customer.id;
        this.lockerId = order.locker.id;

        this.orderItemsIds = new ArrayList<>();
        for (OrderItem orderItem : order.orderItems) {
            this.orderItemsIds.add(orderItem.id);
        }
    }

    public Order toOrder(
            CustomerService customerService,
            LockerService lockerService,
            OrderItemService orderItemService
    ) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Long orderItemId : this.orderItemsIds) {
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
