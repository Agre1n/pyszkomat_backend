package net.springboot.pyszkomat_backend.dto.frontend;

import java.util.List;

public class NewOrderFrontendDto {

    public String parcelMachineId;
    public Long customerId;
    public List<NewOrderItemFrontendDto> orderItems;

    public NewOrderFrontendDto() {
    }
}
