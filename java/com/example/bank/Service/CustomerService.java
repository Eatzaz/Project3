package com.example.bank.Service;

import com.example.bank.Api.ApiException;
import com.example.bank.DTO.CustomerDTO;
import com.example.bank.Model.Customer;
import com.example.bank.Model.User;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public Customer getCustomer(Integer user_id) {
        User user = userRepository.findUserById(user_id);
        if (user == null) {
            throw new ApiException("Customer Not Found");
        }
return customerRepository.findCustomerByUser(user);
    }
    public void addCustomer(User user,CustomerDTO customerDTO){
 user.setRole("CUSTOMER");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());//اشفر الباسوورد
        user.setPassword(hashPassword);
        userRepository.save(user);
}
Customer customer1=new Customer(null, customerDTO.getPhoneNumber(),user,null);
customerRepository.save(customer1);
    }
    public void update(Integer user_id,Integer customer_id,CustomerDTO customerDTO){
        User user=userRepository.findUserById(user_id);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if(user==null){
            throw new ApiException("user Not Found");
        }
        if(customer==null){
            throw new ApiException("customer Not Found");
        }
        if(customerDTO.getUser_id()!=user_id){
            throw new ApiException("error");
        }
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        Customer customer1=new Customer(null, customerDTO.getPhoneNumber(),user,null);
        customerRepository.save(customer1);
    }
    public void delete(Integer user_id,Integer customer_id){
Customer customer=customerRepository.findCustomerById(customer_id);
        if(customer==null){
            throw new ApiException("user Not Found");
        }
        if(customer.getUser().getId()!=user_id){
            throw new ApiException("error");
        }
        customerRepository.delete(customer);
    }
}
