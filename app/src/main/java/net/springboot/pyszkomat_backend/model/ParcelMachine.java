package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parcel_machines")
public class ParcelMachine {

    @Id
    private String id;

    private double latitude;

    private double longitude;

    private String address;

    @OneToMany(mappedBy = "parcelMachine", cascade = CascadeType.ALL)
    private List<Locker> lockers;

    @ManyToMany
    @JoinTable(
            name = "parcel_machine_restaurant",
            joinColumns = @JoinColumn(name = "parcel_machine_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    private List<Restaurant> restaurants;

    public ParcelMachine() {
    }

    public ParcelMachine(
            String id,
            double latitude,
            double longitude,
            String address,
            List<Locker> lockers,
            List<Restaurant> restaurants
    ) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;

        this.setLockers(lockers);
        this.setRestaurants(restaurants);
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

    public List<Locker> getLockers() {
        return lockers;
    }

    public void setLockers(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public void addLocker(Locker locker) {
        lockers.add(locker);
    }

    public void removeLocker(Locker locker) {
        lockers.remove(locker);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public void removeRestaurant(Restaurant restaurant) {
        restaurants.remove(restaurant);
    }
}