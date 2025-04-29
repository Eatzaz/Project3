package com.example.bank.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    @NotEmpty(message = "Phone Number Can't be Empty")
    @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;
        private Integer id;
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
