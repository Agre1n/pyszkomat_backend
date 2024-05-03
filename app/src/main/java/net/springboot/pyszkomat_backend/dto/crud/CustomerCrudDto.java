package net.springboot.pyszkomat_backend.dto.crud;

import net.springboot.pyszkomat_backend.model.Customer;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class CustomerCrudDto {

    public Long id;
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String address;
    public List<Long> ordersIds;

    public CustomerCrudDto() {
    }

    public CustomerCrudDto(Customer customer) {
        id = customer.id;
        firstName = customer.firstName;
        lastName = customer.lastName;
        email = customer.email;
        phoneNumber = customer.phoneNumber;
        address = customer.address;

        ordersIds = new ArrayList<>();
        for (Order order : customer.orders) {
            ordersIds.add(order.id);
        }
    }

    public Customer toCustomer(OrderService orderService) {
        List<Order> orders = new ArrayList<>();
        for (Long id : ordersIds) {
            orders.add(orderService.getOrder(id));
        }

        return new Customer(
                id,
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                orders
        );
    }
}
