package com.example.bank.Controller;

import com.example.bank.Api.ApiResponse;
import com.example.bank.DTO.CustomerDTO;
import com.example.bank.Model.Account;
import com.example.bank.Model.Customer;
import com.example.bank.Model.User;
import com.example.bank.Repository.AccountRepository;
import com.example.bank.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/get")
    public ResponseEntity getMyAcount(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(accountService.getMyAccount((user.getId())));
    }
    @PostMapping("/add")
    public ResponseEntity addAccount(@AuthenticationPrincipal User user, @RequestBody @Valid Account account){
        accountService.add(user.getId(),account);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );
    }
    @PutMapping("/update/{account_id}")
    public ResponseEntity update(@PathVariable Integer account_id,@AuthenticationPrincipal User user,@RequestBody @Valid Account account){
        accountService.update(user.getId(),account_id,account);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity delete(@AuthenticationPrincipal User user,@PathVariable Integer account_id)
    {
        accountService.delete(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
    @PutMapping("/active/{account_id}")
    public ResponseEntity active(@AuthenticationPrincipal User user,@PathVariable Integer account_id)
    {
        accountService.activeAccount(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
    @GetMapping("/ditails/{account_id}")
    public ResponseEntity getDitailsAccount(@AuthenticationPrincipal User user,@PathVariable Integer account_id)
    {
        accountService.getDitailsAccount(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
    @GetMapping("/List")
    public ResponseEntity getListUsers(@AuthenticationPrincipal User user)
    {
        List<Customer> customers=accountService.getListUsers(user.getId());
        return ResponseEntity.status(200).body( customers);
    }
    @PutMapping("/deposit/{account_id}/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user,@PathVariable Integer account_id,@PathVariable Integer amount)
    {
        accountService.deposit(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
    @PutMapping("/withdraw/{account_id}/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user,@PathVariable Integer account_id,@PathVariable Integer amount)
    {
        accountService.withdraw(user.getId(),account_id,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );
    }
    @PutMapping("/transfer/{account_id1}/{account_id2}/{amount}")
    public ResponseEntity transfer(@AuthenticationPrincipal User user,@PathVariable Integer account_id1,@PathVariable Integer account_id2,@PathVariable Integer amount)
    {
        accountService.transfer(user.getId(),account_id1,account_id2,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );
    }
    @PutMapping("/blocked/{account_id1}/{account_id}")
    public ResponseEntity Blocked(@AuthenticationPrincipal User user,@PathVariable Integer account_id)
    {
        accountService.Blocked(user.getId(),account_id);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );
    }


}
