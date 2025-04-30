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
     public void registration(CustomerDTO customerDTO){
        User user=new User();
        user.setRole("CUSTOMER");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());//اشفر الباسوورد
        user.setPassword(hashPassword);
        user.setEmail(customerDTO.getEmail());
        user.setName(customerDTO.getName());
        user.setUsername(customerDTO.getUsername());
        userRepository.save(user);
        Customer customer=new Customer(null, customerDTO.getPhoneNumber(),user,null);
        customerRepository.save(customer);
      
}
    public void update(Integer user_id,CustomerDTO customerDTO){
        User user=userRepository.findUserById(user_id);

        Customer customer=customerRepository.findCustomerById(customer_id);
        if(user==null){
            throw new ApiException("user Not Found");
        }
        user.setPassword(hashPassword);
        user.setEmail(customerDTO.getEmail());
        user.setName(customerDTO.getName());
        user.setUsername(customerDTO.getUsername());
        userRepository.save(user);
        Customer customer=new Customer(null, customerDTO.getPhoneNumber(),user,null);
        customerRepository.save(customer);
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
