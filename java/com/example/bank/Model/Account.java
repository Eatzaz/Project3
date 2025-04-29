package com.example.bank.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "account Number can't be bull")
    @JsonFormat(pattern = "XXXX-XXXX-XXXX-XXXX")
    private String accountNumber;
    @NotNull(message = " balance can't be bull")
@Positive
    private Double balance;
    private Boolean isActive;
    @ManyToOne
    @JsonIgnore
//    @JoinColumn(name = "customer_id",referencedColumnName = "id") """"problem""""
    private Customer customer;
}
