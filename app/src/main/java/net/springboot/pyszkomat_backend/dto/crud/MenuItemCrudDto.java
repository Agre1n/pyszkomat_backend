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

    public MenuItemCrudDto() {
    }

    public MenuItemCrudDto(MenuItem menuItem) {
        id = menuItem.id;
        name = menuItem.name;
        description = menuItem.description;
        category = menuItem.category;
        price = menuItem.price;
        photoUrl = menuItem.photoUrl;
        restaurantId = menuItem.restaurant.id;
    }

    public MenuItem toMenuItem(RestaurantService restaurantService) {
        return new MenuItem(
                id,
                name,
                description,
                category,
                price,
                photoUrl,
                restaurantService.getRestaurant(restaurantId)
        );
    }
}
