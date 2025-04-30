package com.example.bank.Config;

import com.example.bank.Repository.UserRepository;
import com.example.bank.Service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
@RequiredArgsConstructor
public class Configuration {
private final MyUserDetailsService myUserDetailsService;
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return authenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(daoAuthenticationProvider()) // جايه من فوق
                .authorizeHttpRequests()// حددت من له الصلاحيه
                .requestMatchers("api/v1/user/get").hasAuthority("ADMIN")
                .requestMatchers("api/v1/user/update/{idUser}").hasAuthority("USER")
                .requestMatchers("api/v1/user/delete/{idUser}").hasAuthority("USER")
                .requestMatchers("api/v1/customer/get").hasAnyAuthority("ADMIN","CUSTOMER")
                .requestMatchers("api/v1/customer/add").permitAll()
                .requestMatchers("api/v1/customer/update").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/customer/delete/{customer_id}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/employee/get").hasAnyAuthority("ADMIN","EMPLOYEE")
                .requestMatchers("api/v1/employee/add").permitAll()
                .requestMatchers("api/v1/employee/update").hasAuthority("EMPLOYEE")
                .requestMatchers("api/v1/employee/delete/{employee_id}").hasAuthority("EMPLOYEE")
                .requestMatchers("api/v1/account/get").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/add").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/update/{account_id}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/delete/{account_id}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/active/{account_id}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/ditails/{account_id}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/List").hasAuthority("ADMIN")
                .requestMatchers("api/v1/account/deposit/{account_id}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/withdraw/{account_id}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/transfer/{account_id1}/{account_id1}/{amount}").hasAuthority("CUSTOMER")
                .requestMatchers("api/v1/account/blocked/{account_id1}/{account_id}").hasAuthority("CUSTOMER")
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutUrl("api/v1/auth/logout") //احدد باث ونجمتين يعني كل حاجه يدخلها
                .deleteCookies("JSESSIONID") // هنا اسم الكوكيز خاصه في بوستمان بس
                .invalidateHttpSession(true)
                .and()
                .httpBasic();
        return httpSecurity.build();

        // disable
    }
}
