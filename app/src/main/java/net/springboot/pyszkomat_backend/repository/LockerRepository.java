package net.springboot.pyszkomat_backend.repository;

import net.springboot.pyszkomat_backend.model.Locker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LockerRepository extends JpaRepository<Locker, Long> {
}
