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

    public RestaurantCrudDto(Restaurant restaurant) {
        this.id = restaurant.id;
        this.name = restaurant.name;
        this.address = restaurant.address;
        this.phoneNumber = restaurant.phoneNumber;
        this.description = restaurant.description;
        this.category = restaurant.category;
        this.rating = restaurant.rating;
        this.photoUrl = restaurant.photoUrl;

        this.menuItemsIds = new ArrayList<>();
        for (MenuItem menuItem : restaurant.menuItems) {
            this.menuItemsIds.add(menuItem.id);
        }

        this.parcelMachinesIds = new ArrayList<>();
        for (ParcelMachine parcelMachine : restaurant.parcelMachines) {
            this.parcelMachinesIds.add(parcelMachine.id);
        }
    }

    public Restaurant toRestaurant(MenuItemService menuItemService, ParcelMachineService parcelMachineService) {
        List<MenuItem> menuItems = new ArrayList<>();
        for (Long menuItemId : this.menuItemsIds) {
            menuItems.add(menuItemService.getMenuItem(menuItemId));
        }

        List<ParcelMachine> parcelMachines = new ArrayList<>();
        for (String parcelMachineId : this.parcelMachinesIds) {
            parcelMachines.add(parcelMachineService.getParcelMachine(parcelMachineId));
        }

        return new Restaurant(
                this.id,
                this.name,
                this.address,
                this.phoneNumber,
                this.description,
                this.category,
                this.rating,
                this.photoUrl,
                menuItems,
                parcelMachines
        );
    }
}
