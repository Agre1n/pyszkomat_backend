package net.springboot.pyszkomat_backend.repository;

import net.springboot.pyszkomat_backend.model.ParcelMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcelMachineRepository extends JpaRepository<ParcelMachine, String> {
}
