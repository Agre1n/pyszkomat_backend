package com.example.pyszkomat_backend.repository;

import com.example.pyszkomat_backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
