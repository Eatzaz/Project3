package com.example.bank.Controller;

import com.example.bank.Api.ApiResponse;
import com.example.bank.DTO.CustomerDTO;
import com.example.bank.Model.User;
import com.example.bank.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("/get")
    public ResponseEntity getCustomer(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(customerService.getCustomer(user.getId()));
    }
   @PostMapping("/add")
    public ResponseEntity registration(@AuthenticationPrincipal User user, @RequestBody @Valid CustomerDTO  customerDTO ){
        customerService.registration(user.getId(),customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );
    }
   @PutMapping("/update")
public ResponseEntity update(@AuthenticationPrincipal User user,@RequestBody @Valid CustomerDTO customerDTO){
        customerService.update(user.getId(),customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );
    }
    @DeleteMapping("/delete/{customer_id}")
    public ResponseEntity delete(@AuthenticationPrincipal User user,@PathVariable Integer customer_id)
    {
        customerService.delete(user.getId(),customer_id);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
}
