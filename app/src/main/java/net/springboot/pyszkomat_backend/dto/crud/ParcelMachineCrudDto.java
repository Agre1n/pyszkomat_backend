package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.model.Restaurant;
import net.springboot.pyszkomat_backend.service.LockerService;
import net.springboot.pyszkomat_backend.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

public class ParcelMachineCrudDto {

    public String id;
    public double latitude;
    public double longitude;
    public String address;
    public List<Long> lockersIds;
    public List<Long> restaurantsIds;

    public ParcelMachineCrudDto(ParcelMachine parcelMachine) {
        this.id = parcelMachine.id;
        this.latitude = parcelMachine.latitude;
        this.longitude = parcelMachine.longitude;
        this.address = parcelMachine.address;

        this.lockersIds = new ArrayList<>();
        for (Locker locker : parcelMachine.lockers) {
            this.lockersIds.add(locker.id);
        }

        this.restaurantsIds = new ArrayList<>();
        for (Restaurant restaurant : parcelMachine.restaurants) {
            this.restaurantsIds.add(restaurant.id);
        }
    }

    public ParcelMachine toParcelMachine(LockerService lockerService, RestaurantService restaurantService) {
        List<Locker> lockers = new ArrayList<>();
        for (Long lockerId : this.lockersIds) {
            lockers.add(lockerService.getLocker(lockerId));
        }

        List<Restaurant> restaurants = new ArrayList<>();
        for (Long restaurantId : this.restaurantsIds) {
            restaurants.add(restaurantService.getRestaurant(restaurantId));
        }

        return new ParcelMachine(
                this.id,
                this.latitude,
                this.longitude,
                this.address,
                lockers,
                restaurants
        );
    }
}
