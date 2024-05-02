package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.service.ParcelMachineService;

public class LockerCrudDto {

    public Long id;
    public boolean isEmpty;
    public String parcelMachineId;

    public LockerCrudDto(Locker locker) {
        this.id = locker.id;
        this.isEmpty = locker.isEmpty;
        this.parcelMachineId = locker.parcelMachine.id;
    }

    public Locker toLocker(ParcelMachineService parcelMachineService) {
        return new Locker(
                this.id,
                this.isEmpty,
                parcelMachineService.getParcelMachine(this.parcelMachineId)
        );
    }
}
