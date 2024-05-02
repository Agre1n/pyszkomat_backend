package net.springboot.pyszkomat_backend.repository;

import net.springboot.pyszkomat_backend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
