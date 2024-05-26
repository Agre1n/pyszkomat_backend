package net.springboot.pyszkomat_backend.dto.frontend;

import net.springboot.pyszkomat_backend.model.ParcelMachine;

public class ParcelMachineFrontendDto {

    public String id;
    public double latitude;
    public double longitude;
    public String address;

    public ParcelMachineFrontendDto() {
    }

    public ParcelMachineFrontendDto(ParcelMachine parcelMachine) {
        id = parcelMachine.id;
        latitude = parcelMachine.latitude;
        longitude = parcelMachine.longitude;
        address = parcelMachine.address;
    }
}
