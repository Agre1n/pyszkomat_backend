package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "parcel_machines")
public class ParcelMachine {

    @Id
    public String id;

    public double latitude;

    public double longitude;

    public String address;

    @OneToMany(mappedBy = "parcelMachine", cascade = CascadeType.ALL)
    public List<Locker> lockers;

    @ManyToMany
    @JoinTable(
            name = "parcel_machine_restaurant",
            joinColumns = @JoinColumn(name = "parcel_machine_id"),
            inverseJoinColumns = @JoinColumn(name = "restaurant_id")
    )
    public List<Restaurant> restaurants;

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
        this.lockers = lockers;
        this.restaurants = restaurants;
    }
}
