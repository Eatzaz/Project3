package com.example.bank.Service;

import com.example.bank.Api.ApiException;
import com.example.bank.DTO.CustomerDTO;
import com.example.bank.Model.User;
import com.example.bank.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    // by admin
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public void registration(User user){
//        user.setRole("CUSTOMER");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());//اشفر الباسوورد
        user.setPassword(hashPassword);
        userRepository.save(user);
    }
    public void updateUser(Integer idUser,User user){
        User user1=userRepository.findUserById(idUser);
        if(user1==null){
            throw new ApiException("User Not Found");
        }
        user1.setUsername(user.getUsername());
        user1.setEmail(user.getEmail());
        user1.setName(user.getName());
        user1.setRole(user.getRole());
        user1.setPassword(user.getPassword());
        userRepository.save(user1);
    }
    public void deleteUser(Integer idUser) {
        User user = userRepository.findUserById(idUser);
        if (user == null) {
            throw new ApiException("User Not Found");
        }
        userRepository.delete(user);
    }
    }

