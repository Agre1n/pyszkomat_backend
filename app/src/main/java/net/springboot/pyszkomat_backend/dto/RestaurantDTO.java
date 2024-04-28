package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;
import net.springboot.pyszkomat_backend.model.MenuItem;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDTO {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private String description;
    private RestaurantCategory category;
    private int rating;
    private String photoUrl;
    private List<Long> menuItemsIds;
    private List<String> parcelMachinesIds;

    public RestaurantDTO() {
    }

    public RestaurantDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.address = restaurant.getAddress();
        this.phoneNumber = restaurant.getPhoneNumber();
        this.description = restaurant.getDescription();
        this.category = restaurant.getCategory();
        this.rating = restaurant.getRating();
        this.photoUrl = restaurant.getPhotoUrl();
        this.menuItemsIds = new ArrayList<>();
        this.parcelMachinesIds = new ArrayList<>();

        for (MenuItem menuItem : restaurant.getMenuItems()) {
            this.menuItemsIds.add(menuItem.getId());
        }

        for (ParcelMachine parcelMachine : restaurant.getParcelMachines()) {
            this.parcelMachinesIds.add(parcelMachine.getId());
        }
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

    public List<Long> getMenuItemsIds() {
        return menuItemsIds;
    }

    public void setMenuItemsIds(List<Long> menuItemsIds) {
        this.menuItemsIds = menuItemsIds;
    }

    public List<String> getParcelMachinesIds() {
        return parcelMachinesIds;
    }

    public void setParcelMachinesIds(List<String> parcelMachinesIds) {
        this.parcelMachinesIds = parcelMachinesIds;
    }
}
