package com.example.bank.Service;

import com.example.bank.Api.ApiException;
import com.example.bank.Model.Account;
import com.example.bank.Model.Customer;
import com.example.bank.Model.User;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.Repository.CustomerRepository;
import com.example.bank.Repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public List<Account> getMyAccount(Integer customer_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("User Not Found");
        }

        return accountRepository.findAllByCustomer(customer);
    }

    public void add(Integer customer_id, Account account) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        account.setCustomer(customer);
        account.setIsActive(false);
        accountRepository.save(account);
    }

    public void update(Integer accountId, Integer customerId, Account account) {
        Account account1 = accountRepository.findAccountById(accountId);
        Customer customer = customerRepository.findCustomerById(customerId);
        if (account1 == null) {
            throw new ApiException("Account Not Found");
        }
        if (customer == null) {
            throw new ApiException("customer Not Found");
        }
        if (account1.getCustomer().getId() != customerId) {
            throw new ApiException("Error");
        }
        account1.setAccountNumber(account.getAccountNumber());
        account1.setBalance(account.getBalance());
        accountRepository.save(account1);
    }

    public void delete(Integer accountId, Integer customerId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("accoubt Not Found");
        }
        if (account.getCustomer().getId().equals(customerId)) {
            accountRepository.delete(account);
        }

    }
    public void activeAccount( Integer customerId,Integer account_id){
        Account account = accountRepository.findAccountById(account_id);
        Customer customer=customerRepository.findCustomerById(customerId);
        if (account == null) {
            throw new ApiException("account Not Found");
        }
        if (customer == null) {
            throw new ApiException("customer Not Found");
        }
        account.setIsActive(true);
        accountRepository.save(account);
    }
    public Account getDitailsAccount(Integer customer,Integer account_id) {
Account account=accountRepository.findAccountById(account_id);
if (account == null) {
            throw new ApiException("User Not Found");
        }
        if (customer == null) {
            throw new ApiException("customer Not Found");
        }

         return account;
    }
public List<Customer> getListUsers(Integer user_id){
        User user = userRepository.findUserById(user_id);
//        Customer customer=customerRepository.findCustomerById(user.getCustomer().getId());
    if (user == null) {
        throw new ApiException("Customer Not Found");
    }
    return customerRepository.findCustomersByUser(user);
}
public void deposit(Integer customer_id,Integer account_id,Integer amount){
    Account account = accountRepository.findAccountById(account_id);
    Customer customer=customerRepository.findCustomerById(customer_id);
    if (account == null) {
        throw new ApiException("account Not Found");
    }
    if (customer == null) {
        throw new ApiException("customer Not Found");
    }
if(! account.getCustomer().equals(customer_id)){
    throw new ApiException("Error");
}
account.setBalance(account.getBalance()+amount);
accountRepository.save(account);
}
    public void withdraw(Integer customer_id,Integer account_id,Integer amount){
        Account account = accountRepository.findAccountById(account_id);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if (account == null) {
            throw new ApiException("account Not Found");
        }
        if (customer == null) {
            throw new ApiException("customer Not Found");
        }
        if(! account.getCustomer().equals(customer_id)){
            throw new ApiException("Error");
        }
        if(account.getBalance()<=amount){
            throw new ApiException("Balance does not allow");
        }
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }
    public void transfer(Integer customer_id,Integer account_id1,Integer account_id2,Integer amount){
        Account account=accountRepository.findAccountById(account_id1);
        Account account1=accountRepository.findAccountById(account_id2);
        Customer customer=customerRepository.findCustomerById(customer_id);
        if (customer == null) {
            throw new ApiException("customer Not Found");
        }
        if (account == null) {
            throw new ApiException("account Not Found");
        }
        if (account1 == null) {
            throw new ApiException("account Not Found");
        }
        if(! customer.getAccount().equals(customer_id)){
            throw new ApiException("Error");
        }
        if(account.getBalance()<=amount){
            throw new ApiException("Balance does not allow");
        }
        account.setBalance(account.getBalance() - amount);
        account1.setBalance(account1.getBalance() + account_id1);
        accountRepository.save(account);
        accountRepository.save(account1);
    }
    public void Blocked(Integer user_id,Integer account_id){
        User user=userRepository.findUserById(user_id);
        Account account =accountRepository.findAccountById(account_id);
        if(user==null){
            throw new ApiException("admin Not Found");
        }
        if (account == null) {
            throw new ApiException("account Not Found");
        }
        if(! user.getRole().equals("ADMIN")){
            throw new ApiException("Error");
        }
        account.setIsActive(false);
        accountRepository.save(account);
    }
}