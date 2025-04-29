package com.example.bank.Controller;

import com.example.bank.Api.ApiResponse;
import com.example.bank.Model.User;
import com.example.bank.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getAllUser(){
        return ResponseEntity.status(200).body(userService.getAllUser());
    }
    @PostMapping("/add")
    public ResponseEntity regCustomer(@RequestBody @Valid User user){
        userService.registration(user);
        return ResponseEntity.status(200).body(new ApiResponse("success"));
    }
    @PutMapping("/update/{idUser}")
    public ResponseEntity update(@PathVariable Integer idUser,@RequestBody @Valid User user){
        userService.updateUser(idUser,user);
        return ResponseEntity.status(200).body(new ApiResponse("success"));
    }
    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity delete(@PathVariable Integer idUser){
        userService.deleteUser(idUser);
        return ResponseEntity.status(200).body(new ApiResponse("success"));
    }



}
