package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lockers")
public class Locker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isEmpty;

    @ManyToOne
    @JoinColumn(name = "parcel_machine_id", nullable = false)
    private ParcelMachine parcelMachine;

    public Locker() {
    }

    public Locker(boolean isEmpty, ParcelMachine parcelMachine) {
        this.isEmpty = isEmpty;

        this.setParcelMachine(parcelMachine);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public ParcelMachine getParcelMachine() {
        return parcelMachine;
    }

    public void setParcelMachine(ParcelMachine parcelMachine) {
        this.parcelMachine = parcelMachine;
    }
}