package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.repository.ParcelMachineRepository;

public class LockerCrudDto {

    private Long id;
    private boolean isEmpty;
    private String parcelMachineId;

    public LockerCrudDto() {
    }

    public LockerCrudDto(Locker locker) {
        this.id = locker.getId();
        this.isEmpty = locker.getIsEmpty();
        this.parcelMachineId = locker.getParcelMachine().getId();
    }

    public Locker toLocker(ParcelMachineRepository parcelMachineRepository) {
        return new Locker(
                this.id,
                this.isEmpty,
                parcelMachineRepository.findById(this.parcelMachineId).orElse(null)
        );
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
