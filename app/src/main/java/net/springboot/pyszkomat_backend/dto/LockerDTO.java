package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.model.Locker;

public class LockerDTO {

    private Long id;
    private boolean isEmpty;
    private String parcelMachineId;

    public LockerDTO() {
    }

    public LockerDTO(Locker locker) {
        this.id = locker.getId();
        this.isEmpty = locker.getIsEmpty();
        this.parcelMachineId = locker.getParcelMachine().getId();
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

    public String getParcelMachineId() {
        return parcelMachineId;
    }

    public void setParcelMachineId(String parcelMachineId) {
        this.parcelMachineId = parcelMachineId;
    }
}
