package com.example.bank.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {
    private Integer user_id;
    @NotEmpty(message = "position Can't be Empty")
    private String position;
    @NotNull(message = "salary Can't be null")
    @Positive
    private Double salary;
     private String username;
    @NotEmpty(message = "password Can't Empty")
    private String password;
    @NotEmpty(message = "Name Can't Empty")
    @Size(min = 2, max = 20)
    private String name;
    @NotEmpty(message = "Email Can't Empty")
    @Email
    private String email;
    @NotEmpty(message = "Role Can't Empty")
    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN")
    private String role;

}
