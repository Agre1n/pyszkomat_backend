package net.springboot.pyszkomat_backend.repository;

import net.springboot.pyszkomat_backend.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
