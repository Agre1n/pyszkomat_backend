package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;
import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;

import java.util.List;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String description;

    @Enumerated(EnumType.STRING)
    private RestaurantCategory category;

    private int rating;

    private String photoUrl;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuItem> menuItems;

    @ManyToMany(mappedBy = "restaurants")
    private List<ParcelMachine> parcelMachines;

    public Restaurant() {
    }

    public Restaurant(
            Long id,
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
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.category = category;
        this.rating = rating;
        this.photoUrl = photoUrl;

        this.setMenuItems(menuItems);
        this.setParcelMachines(parcelMachines);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RestaurantCategory getCategory() {
        return category;
    }

    public void setCategory(RestaurantCategory category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void removeMenuItem(MenuItem menuItem) {
        this.menuItems.remove(menuItem);
    }

    public List<ParcelMachine> getParcelMachines() {
        return parcelMachines;
    }

    public void setParcelMachines(List<ParcelMachine> parcelMachines) {
        this.parcelMachines = parcelMachines;
    }

    public void addParcelMachine(ParcelMachine parcelMachine) {
        this.parcelMachines.add(parcelMachine);
    }

    public void removeParcelMachine(ParcelMachine parcelMachine) {
        this.parcelMachines.remove(parcelMachine);
    }
}