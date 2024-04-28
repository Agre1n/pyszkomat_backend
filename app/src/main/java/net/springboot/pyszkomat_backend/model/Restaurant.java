package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;

import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String address;

    public String phoneNumber;

    public String description;

    @Enumerated(EnumType.STRING)
    public RestaurantCategory category;

    @Max(5)
    @Min(0)
    public int rating;

    public String photoUrl;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    public List<MenuItem> menuItems;

    @ManyToMany(mappedBy = "restaurants")
    public List<ParcelMachine> parcelMachines;

    public Restaurant() {
    }

    public Restaurant(
            String name,
            String address,
            String phoneNumber,
            String description,
            RestaurantCategory category,
            int rating,
            String photoUrl,
            List<MenuItem> menuItems,
            List<ParcelMachine> parcelMachines
    ) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.category = category;
        this.rating = rating;
        this.photoUrl = photoUrl;
        this.menuItems = menuItems;
        this.parcelMachines = parcelMachines;
    }
}
