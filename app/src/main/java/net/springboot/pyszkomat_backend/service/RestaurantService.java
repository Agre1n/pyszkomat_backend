package net.springboot.pyszkomat_backend.service;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.Restaurant;
import net.springboot.pyszkomat_backend.repository.RestaurantRepository;

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ParcelMachine with id : " + id + " not found"));
    }

    public Iterable<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant addRestaurant(@Valid Restaurant restaurant) {
        restaurant.id = null;
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long id, @Valid Restaurant restaurant) {
        Restaurant _ = this.getRestaurant(id);

        restaurant.id = id;
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}
