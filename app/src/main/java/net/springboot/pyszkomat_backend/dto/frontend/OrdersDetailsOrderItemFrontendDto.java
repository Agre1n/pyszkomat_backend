package net.springboot.pyszkomat_backend.dto.frontend;

public class OrdersDetailsOrderItemFrontendDto {

    public String name;
    public int quantity;

    public OrdersDetailsOrderItemFrontendDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
