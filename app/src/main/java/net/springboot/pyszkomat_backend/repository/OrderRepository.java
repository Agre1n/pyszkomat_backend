package net.springboot.pyszkomat_backend.repository;

import net.springboot.pyszkomat_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
