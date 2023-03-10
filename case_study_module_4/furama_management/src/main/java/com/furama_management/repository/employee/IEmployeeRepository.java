package com.furama_management.repository.employee;

import com.furama_management.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findAll();
}
