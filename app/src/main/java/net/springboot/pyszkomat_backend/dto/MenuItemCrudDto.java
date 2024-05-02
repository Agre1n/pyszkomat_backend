package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.enumeration.MenuItemCategory;
import net.springboot.pyszkomat_backend.model.MenuItem;
import net.springboot.pyszkomat_backend.model.Restaurant;
import net.springboot.pyszkomat_backend.repository.RestaurantRepository;

public class MenuItemCrudDto {

    private Long id;
    private String name;
    private String description;
    private MenuItemCategory category;
    private float price;
    private String photoUrl;
    private Long restaurantId;

    public MenuItemCrudDto() {
    }

    public MenuItemCrudDto(MenuItem menuItem) {
        this.id = menuItem.getId();
        this.name = menuItem.getName();
        this.description = menuItem.getDescription();
        this.category = menuItem.getCategory();
        this.price = menuItem.getPrice();
        this.photoUrl = menuItem.getPhotoUrl();
        this.restaurantId = menuItem.getRestaurant().getId();
    }

    public MenuItem toMenuItem(RestaurantRepository restaurantRepository) {
        return new MenuItem(
                this.id,
                this.name,
                this.description,
                this.category,
                this.price,
                this.photoUrl,
                restaurantRepository.findById(this.restaurantId).orElse(null)
        );
    }

    public MenuItem toMenuItem(Restaurant restaurant) {
        return new MenuItem(
                this.id,
                this.name,
                this.description,
                this.category,
                this.price,
                this.photoUrl,
                restaurant
        );
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MenuItemCategory getCategory() {
        return category;
    }

    public void setCategory(MenuItemCategory category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
