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

    public CustomerCrudDto(Customer customer) {
        this.id = customer.id;
        this.firstName = customer.firstName;
        this.lastName = customer.lastName;
        this.email = customer.email;
        this.phoneNumber = customer.phoneNumber;
        this.address = customer.address;

        this.ordersIds = new ArrayList<>();
        for (Order order : customer.orders) {
            this.ordersIds.add(order.id);
        }
    }

    public Customer toCustomer(OrderService orderService) {
        List<Order> orders = new ArrayList<>();
        for (Long id : this.ordersIds) {
            orders.add(orderService.getOrder(id));
        }

        return new Customer(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.phoneNumber,
                this.address,
                orders
        );
    }
}
