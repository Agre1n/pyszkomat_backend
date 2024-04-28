package net.springboot.pyszkomat_backend.dto;

import net.springboot.pyszkomat_backend.model.Customer;
import net.springboot.pyszkomat_backend.model.Order;
import net.springboot.pyszkomat_backend.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private List<Long> ordersIds;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.address = customer.getAddress();
        this.ordersIds = new ArrayList<>();

        for (Order order : customer.getOrders()) {
            this.ordersIds.add(order.getId());
        }
    }

    public Customer toCustomer(OrderRepository orderRepository) {
        Customer customer = new Customer();

        customer.setId(this.id);
        customer.setFirstName(this.firstName);
        customer.setLastName(this.lastName);
        customer.setEmail(this.email);
        customer.setPhoneNumber(this.phoneNumber);
        customer.setAddress(this.address);

        List<Order> orders = new ArrayList<>();
        for (Long orderId : this.ordersIds) {
            orderRepository.findById(orderId).ifPresent(orders::add);
        }
        customer.setOrders(orders);

        return customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Long> getOrdersIds() {
        return ordersIds;
    }

    public void setOrdersIds(List<Long> ordersIds) {
        this.ordersIds = ordersIds;
    }
}
