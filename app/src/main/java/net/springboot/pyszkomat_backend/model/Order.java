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
    private Long id;

    private LocalDateTime orderTime;

    private LocalDateTime deliveryTime;

    private LocalDateTime pickUpTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private boolean isHeated;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "locker_id", nullable = false)
    private Locker locker;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(
            LocalDateTime orderTime,
            LocalDateTime deliveryTime,
            LocalDateTime pickUpTime,
            OrderStatus status,
            boolean isHeated
    ) {
        super();
        this.orderTime = orderTime;
        this.deliveryTime = deliveryTime;
        this.pickUpTime = pickUpTime;
        this.status = status;
        this.isHeated = isHeated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public LocalDateTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalDateTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public boolean getIsHeated() {
        return isHeated;
    }

    public void setHeated(boolean heated) {
        isHeated = heated;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
