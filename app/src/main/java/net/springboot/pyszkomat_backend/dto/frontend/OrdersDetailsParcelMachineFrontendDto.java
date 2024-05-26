package net.springboot.pyszkomat_backend.dto.frontend;

public class OrdersDetailsParcelMachineFrontendDto {

    public String parcelMachineId;
    public String address;

    public OrdersDetailsParcelMachineFrontendDto(String parcelMachineId, String address) {
        this.parcelMachineId = parcelMachineId;
        this.address = address;
    }
}
