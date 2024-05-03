package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;
import net.springboot.pyszkomat_backend.model.MenuItem;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.model.Restaurant;
import net.springboot.pyszkomat_backend.service.MenuItemService;
import net.springboot.pyszkomat_backend.service.ParcelMachineService;

import java.util.ArrayList;
import java.util.List;

public class RestaurantCrudDto {

    public Long id;
    public String name;
    public String address;
    public String phoneNumber;
    public String description;
    public RestaurantCategory category;
    public int rating;
    public String photoUrl;
    public List<Long> menuItemsIds;
    public List<String> parcelMachinesIds;

    public RestaurantCrudDto() {
    }

    public RestaurantCrudDto(Restaurant restaurant) {
        id = restaurant.id;
        name = restaurant.name;
        address = restaurant.address;
        phoneNumber = restaurant.phoneNumber;
        description = restaurant.description;
        category = restaurant.category;
        rating = restaurant.rating;
        photoUrl = restaurant.photoUrl;

        menuItemsIds = new ArrayList<>();
        for (MenuItem menuItem : restaurant.menuItems) {
            menuItemsIds.add(menuItem.id);
        }

        parcelMachinesIds = new ArrayList<>();
        for (ParcelMachine parcelMachine : restaurant.parcelMachines) {
            parcelMachinesIds.add(parcelMachine.id);
        }
    }

    public Restaurant toRestaurant(MenuItemService menuItemService, ParcelMachineService parcelMachineService) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (Long menuItemId : menuItemsIds) {
            menuItems.add(menuItemService.getMenuItem(menuItemId));
        }

        List<ParcelMachine> parcelMachines = new ArrayList<>();
        for (String parcelMachineId : parcelMachinesIds) {
            parcelMachines.add(parcelMachineService.getParcelMachine(parcelMachineId));
        }

        return new Restaurant(
                id,
                name,
                address,
                phoneNumber,
                description,
                category,
                rating,
                photoUrl,
                menuItems,
                parcelMachines
        );
    }
}
