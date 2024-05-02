package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lockers")
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public boolean isEmpty;

    @ManyToOne
    @JoinColumn(name = "parcel_machine_id", nullable = false)
    public ParcelMachine parcelMachine;

    public Locker() {
    }

    public Locker(
            Long id,
            boolean isEmpty,
            ParcelMachine parcelMachine
    ) {
        this.id = id;
        this.isEmpty = isEmpty;
        this.parcelMachine = parcelMachine;
    }
}