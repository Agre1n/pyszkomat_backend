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
    private List<Restaurant> restaurants;

    public ParcelMachine() {
    }

    public ParcelMachine(
            String id,
            double latitude,
            double longitude,
            String address
    ) {
        super();
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
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

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }
}
