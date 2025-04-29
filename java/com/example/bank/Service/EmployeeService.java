package com.example.bank.Service;

import com.example.bank.Api.ApiException;
import com.example.bank.DTO.CustomerDTO;
import com.example.bank.DTO.EmployeeDTO;
import com.example.bank.Model.Customer;
import com.example.bank.Model.Employee;
import com.example.bank.Model.User;
import com.example.bank.Repository.EmployeeRepository;
import com.example.bank.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    public Employee getEmployee(Integer user_id){
        User user=userRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("Employee Not Found");
        }
        return employeeRepository.findEmployeeByUser(user);
    }
    public void registration(EmployeeDTO employeeDTO){
         User user=userRepository.findUserById(employeeDTO.getId());
       user.setRole("EMPLOYEE");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());//اشفر الباسوورد
        user.setPassword(hashPassword);
         Employee employee=new Employee(null,employeeDTO.getPosition(),employeeDTO.getSalary(),employeeDTO.getUsername(),employeeDTO.getPassword(),employeeDTO.getName(),employeeDTO.getEmail(),employeeDTO.getRole(),user);
         employeeRepository.save(employee);
        userRepository.save(user);
    }
    public void update(Integer user_id, Integer employee_id, EmployeeDTO employeeDTO){
        User user=userRepository.findUserById(user_id);
        Employee employee=employeeRepository.findEmployeeById(employee_id);
        if(user==null){
            throw new ApiException("user Not Found");
        }
        if(employee==null){
            throw new ApiException("employee Not Found");
        }
        if(employeeDTO.getUser_id()!=user_id){
            throw new ApiException("error");
        }
        employee.setPosition(employeeDTO.getPosition());
        employee.setSalary(employeeDTO.getSalary());
        Employee employee1=new Employee(null,employeeDTO.getPosition(),employeeDTO.getSalary(),user);
        employeeRepository.save(employee1);
    }
    public void delete(Integer user_id,Integer employee_id){
        Employee employee=employeeRepository.findEmployeeById(employee_id);
        if(employee==null){
            throw new ApiException("user Not Found");
        }
        if(employee.getUser().getId()!=user_id){
            throw new ApiException("error");
        }
        employeeRepository.delete(employee);
    }
}
