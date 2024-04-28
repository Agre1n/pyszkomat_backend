package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import net.springboot.pyszkomat_backend.enumeration.MenuItemCategory;

@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String description;

    @Enumerated(EnumType.STRING)
    public MenuItemCategory category;

    @Positive
    public float price;

    public String photoUrl;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    public Restaurant restaurant;

    public MenuItem() {
    }

    public MenuItem(
            String name,
            String description,
            MenuItemCategory category,
            float price,
            String photoUrl,
            Restaurant restaurant
    ) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.photoUrl = photoUrl;
        this.restaurant = restaurant;
    }
}
