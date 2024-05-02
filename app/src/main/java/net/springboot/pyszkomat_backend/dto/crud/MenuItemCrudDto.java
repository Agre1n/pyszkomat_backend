package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.enumeration.MenuItemCategory;
import net.springboot.pyszkomat_backend.model.MenuItem;
import net.springboot.pyszkomat_backend.service.RestaurantService;

public class MenuItemCrudDto {

    public Long id;
    public String name;
    public String description;
    public MenuItemCategory category;
    public float price;
    public String photoUrl;
    public Long restaurantId;

    public MenuItemCrudDto(MenuItem menuItem) {
        this.id = menuItem.id;
        this.name = menuItem.name;
        this.description = menuItem.description;
        this.category = menuItem.category;
        this.price = menuItem.price;
        this.photoUrl = menuItem.photoUrl;
        this.restaurantId = menuItem.restaurant.id;
    }

    public MenuItem toMenuItem(RestaurantService restaurantService) {
        return new MenuItem(
                this.id,
                this.name,
                this.description,
                this.category,
                this.price,
                this.photoUrl,
                restaurantService.getRestaurant(this.restaurantId)
        );
    }
}
