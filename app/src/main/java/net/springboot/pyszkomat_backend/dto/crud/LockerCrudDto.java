package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.model.Locker;
import net.springboot.pyszkomat_backend.service.ParcelMachineService;

public class LockerCrudDto {

    public Long id;
    public boolean isEmpty;
    public String parcelMachineId;

    public LockerCrudDto() {
    }

    public LockerCrudDto(Locker locker) {
        id = locker.id;
        isEmpty = locker.isEmpty;
        parcelMachineId = locker.parcelMachine.id;
    }

    public Locker toLocker(ParcelMachineService parcelMachineService) {
        return new Locker(
                id,
                isEmpty,
                parcelMachineService.getParcelMachine(parcelMachineId)
        );
    }
}
