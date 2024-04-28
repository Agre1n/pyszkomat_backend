package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Positive
    public int quantity;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    public MenuItem menuItem;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    public Order order;

    public OrderItem() {
    }

    public OrderItem(int quantity, MenuItem menuItem, Order order) {
        this.quantity = quantity;
        this.menuItem = menuItem;
        this.order = order;
    }

    public float getTotalPrice() {
        return quantity * menuItem.price;
    }
}
