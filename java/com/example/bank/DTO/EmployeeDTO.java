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

}
