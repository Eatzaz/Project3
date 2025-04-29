package com.example.bank.Repository;

import com.example.bank.Model.Customer;
import com.example.bank.Model.User;
import jakarta.validation.constraints.AssertTrue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findCustomerById(Integer id);
    Customer findCustomerByUser(User user);
    List<Customer>findCustomersByUser(User user);
}
