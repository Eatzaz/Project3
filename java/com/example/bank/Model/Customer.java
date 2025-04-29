package com.example.bank.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Phone Number Can't be Empty")
    @Pattern(regexp = "^05\\d{8}$")
    private String phoneNumber;
    @OneToOne
    @JsonIgnore
    @MapsId
    private User user;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private Set<Account> account;
}
