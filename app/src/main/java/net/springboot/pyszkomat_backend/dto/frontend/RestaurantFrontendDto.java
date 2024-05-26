package net.springboot.pyszkomat_backend.dto.frontend;

import net.springboot.pyszkomat_backend.enumeration.RestaurantCategory;
import net.springboot.pyszkomat_backend.model.Restaurant;

public class RestaurantFrontendDto {

    public Long id;
    public String name;
    public String address;
    public String phoneNumber;
    public String description;
    public RestaurantCategory category;
    public int rating;
    public String photoUrl;

    public RestaurantFrontendDto() {
    }

    public RestaurantFrontendDto(Restaurant restaurant) {
        id = restaurant.id;
        name = restaurant.name;
        address = restaurant.address;
        phoneNumber = restaurant.phoneNumber;
        description = restaurant.description;
        category = restaurant.category;
        rating = restaurant.rating;
        photoUrl = restaurant.photoUrl;
    }
}
