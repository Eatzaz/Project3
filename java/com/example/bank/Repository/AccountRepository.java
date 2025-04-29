package com.example.bank.Repository;

import com.example.bank.Model.Account;
import com.example.bank.Model.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountById(Integer id);
    List<Account> findAllByCustomer(Customer customer);
//    Account findAccountByC
}
