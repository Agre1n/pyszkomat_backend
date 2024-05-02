package net.springboot.pyszkomat_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String firstName;

    public String lastName;

    @Email
    public String email;

    public String phoneNumber;

    public String address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<Order> orders;

    public Customer() {
    }

    public Customer(
            Long id,
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            List<Order> orders
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orders = orders;
    }
}
