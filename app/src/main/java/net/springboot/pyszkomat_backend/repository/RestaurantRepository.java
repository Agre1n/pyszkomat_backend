package net.springboot.pyszkomat_backend.repository;

import net.springboot.pyszkomat_backend.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
