package net.springboot.pyszkomat_backend;

import net.springboot.pyszkomat_backend.enumeration.OrderStatus;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class StatusUpdater {

    private final OrderService orderService;

    public StatusUpdater(OrderService orderService) {
        this.orderService = orderService;
    }

    @Scheduled(cron = "0 * * * * * ")
    public void updateStatus() {
        LocalDateTime now = LocalDateTime.now();

        for (Order order : orderService.getOrders()) {
            if (order.status == OrderStatus.RECEIVED)
                continue;

            if (order.orderTime != null && Duration.between(order.orderTime, now).toMinutes() >= 30)
                order.status = OrderStatus.DELIVERED;
            if (order.deliveryTime != null && order.deliveryTime.isBefore(now))
                order.status = OrderStatus.READY_FOR_PICKUP;
            if (order.pickUpTime != null && order.pickUpTime.isBefore(now))
                order.status = OrderStatus.LOST_AND_FOUND;

            orderService.updateOrder(order.id, order);
        }
    }
}

