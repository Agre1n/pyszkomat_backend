package net.springboot.pyszkomat_backend.dto.frontend;

import net.springboot.pyszkomat_backend.dto.crud.OrderCrudDto;
import net.springboot.pyszkomat_backend.dto.crud.OrderItemCrudDto;

import java.util.List;

public class OrderFrontendDto {
    public OrderCrudDto order;
    public List<OrderItemCrudDto> orderItems;

    public OrderFrontendDto() {
    }
}
