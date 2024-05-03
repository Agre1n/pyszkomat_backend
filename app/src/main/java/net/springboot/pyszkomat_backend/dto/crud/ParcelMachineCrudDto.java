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

    public ParcelMachineCrudDto() {
    }

    public ParcelMachineCrudDto(ParcelMachine parcelMachine) {
        id = parcelMachine.id;
        latitude = parcelMachine.latitude;
        longitude = parcelMachine.longitude;
        address = parcelMachine.address;

        lockersIds = new ArrayList<>();
        for (Locker locker : parcelMachine.lockers) {
            lockersIds.add(locker.id);
        }

        restaurantsIds = new ArrayList<>();
        for (Restaurant restaurant : parcelMachine.restaurants) {
            restaurantsIds.add(restaurant.id);
        }
    }

    public ParcelMachine toParcelMachine(LockerService lockerService, RestaurantService restaurantService) {
        List<Locker> lockers = new ArrayList<>();
        for (Long lockerId : lockersIds) {
            lockers.add(lockerService.getLocker(lockerId));
        }

        List<Restaurant> restaurants = new ArrayList<>();
        for (Long restaurantId : restaurantsIds) {
            restaurants.add(restaurantService.getRestaurant(restaurantId));
        }

        return new ParcelMachine(
                id,
                latitude,
                longitude,
                address,
                lockers,
                restaurants
        );
    }
}
