package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.model.ParcelMachine;

import java.util.ArrayList;
import java.util.List;

public class ParcelMachineDTO {

    private String id;
    private double latitude;
    private double longitude;
    private String address;
    private List<Long> lockersIds;

    public ParcelMachineDTO() {
    }

    public ParcelMachineDTO(ParcelMachine parcelMachine) {
        this.id = parcelMachine.getId();
        this.latitude = parcelMachine.getLatitude();
        this.longitude = parcelMachine.getLongitude();
        this.address = parcelMachine.getAddress();
        this.lockersIds = new ArrayList<>();

        for (Locker locker : parcelMachine.getLockers()) {
            this.lockersIds.add(locker.getId());
        }
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
}
