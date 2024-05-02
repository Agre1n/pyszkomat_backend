package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;
import net.springboot.pyszkomat_backend.enumeration.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDateTime orderTime;

    public LocalDateTime deliveryTime;

    public LocalDateTime pickUpTime;

    @Enumerated(EnumType.STRING)
    public OrderStatus status;

    public boolean isHeated;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    public Customer customer;

    @ManyToOne
    @JoinColumn(name = "locker_id", nullable = true)
    public Locker locker;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    public List<OrderItem> orderItems;

    public Order() {
    }

    public Order(
            Long id,
            LocalDateTime orderTime,
            LocalDateTime deliveryTime,
            LocalDateTime pickUpTime,
            OrderStatus status,
            boolean isHeated,
            Customer customer,
            Locker locker,
            List<OrderItem> orderItems
    ) {
        this.id = id;
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.pickUpTime = pickUpTime;
        this.status = status;
        this.isHeated = isHeated;
        this.customer = customer;
        this.locker = locker;
        this.orderItems = orderItems;
    }
}
