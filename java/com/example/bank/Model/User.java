package com.example.bank.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "UserName Can't Empty")
    @Size(min = 4, max = 10)
    @Column(columnDefinition = "varchar(10) not null")
    private String username;
    @NotEmpty(message = "password Can't Empty")
//    @Size(min = 6)
//    @Column(columnDefinition = "varchar(6) not null")
    private String password;
    @NotEmpty(message = "Name Can't Empty")
    @Size(min = 2, max = 20)
    @Column(columnDefinition = "varchar(20) not null")
    private String name;
    @NotEmpty(message = "Email Can't Empty")
    @Email
    @Column(columnDefinition = "varchar(20) not null unique")
    private String email;
    @NotEmpty(message = "Role Can't Empty")
    @Pattern(regexp = "CUSTOMER|EMPLOYEE|ADMIN")
    @Column(columnDefinition = "varchar(20) not null")
    private String role;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
}