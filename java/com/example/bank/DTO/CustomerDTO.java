package com.example.bank.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDTO {
    private Integer user_id;
    @NotEmpty(message = "Phone Number Can't be Empty")
//    @Pattern(regexp = "^[05]")
    private String phoneNumber;
}
