package com.example.bank.Repository;

import com.example.bank.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById(Integer id);
    User findUserByUsername(String username);
    User findUserByRole(String role);
}
