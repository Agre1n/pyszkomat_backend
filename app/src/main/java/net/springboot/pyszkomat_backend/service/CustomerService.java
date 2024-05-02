package net.springboot.pyszkomat_backend.service;

import jakarta.validation.Valid;
import net.springboot.pyszkomat_backend.exception.ResourceNotFoundException;
import net.springboot.pyszkomat_backend.model.Customer;
import net.springboot.pyszkomat_backend.repository.CustomerRepository;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with id : " + id + " not found"));
    }

    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(@Valid Customer customer) {
        customer.id = null;
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, @Valid Customer customer) {
        Customer _ = this.getCustomer(id);

        customer.id = id;
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
