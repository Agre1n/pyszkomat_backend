package net.springboot.pyszkomat_backend.repository;

import net.springboot.pyszkomat_backend.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
