package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.model.ParcelMachine;
import net.springboot.pyszkomat_backend.model.Restaurant;
import net.springboot.pyszkomat_backend.repository.LockerRepository;
import net.springboot.pyszkomat_backend.repository.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class ParcelMachineCrudDto {

    private String id;
    private double latitude;
    private double longitude;
    private String address;
    private List<Long> lockersIds;
    private List<Long> restaurantsIds;

    public ParcelMachineCrudDto() {
    }

    public ParcelMachineCrudDto(ParcelMachine parcelMachine) {
        this.id = parcelMachine.getId();
        this.latitude = parcelMachine.getLatitude();
        this.longitude = parcelMachine.getLongitude();
        this.address = parcelMachine.getAddress();

        this.lockersIds = new ArrayList<>();
        for (Locker locker : parcelMachine.getLockers()) {
            this.lockersIds.add(locker.getId());
        }

        this.restaurantsIds = new ArrayList<>();
        for (Restaurant restaurant : parcelMachine.getRestaurants()) {
            this.restaurantsIds.add(restaurant.getId());
        }
    }

    public ParcelMachine toParcelMachine(LockerRepository lockerRepository, RestaurantRepository restaurantRepository) {
        List<Locker> lockers = new ArrayList<>();
        for (Long lockerId : this.lockersIds) {
            lockerRepository.findById(lockerId).ifPresent(lockers::add);
        }

        List<Restaurant> restaurants = new ArrayList<>();
        for (Long restaurantId : this.restaurantsIds) {
            restaurantRepository.findById(restaurantId).ifPresent(restaurants::add);
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getLockersIds() {
        return lockersIds;
    }

    public void setLockersIds(List<Long> lockersIds) {
        this.lockersIds = lockersIds;
    }

    public List<Long> getRestaurantsIds() {
        return restaurantsIds;
    }

    public void setRestaurantsIds(List<Long> restaurantsIds) {
        this.restaurantsIds = restaurantsIds;
    }
}
