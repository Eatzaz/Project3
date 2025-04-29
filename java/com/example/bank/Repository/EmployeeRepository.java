package com.example.bank.Repository;

import com.example.bank.Model.Employee;
import com.example.bank.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findEmployeeById(Integer id);
    Employee findEmployeeByUser(User user);
}
