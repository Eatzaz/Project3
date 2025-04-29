package com.example.bank.Controller;

import com.example.bank.Api.ApiResponse;
import com.example.bank.DTO.CustomerDTO;
import com.example.bank.DTO.EmployeeDTO;
import com.example.bank.Model.User;
import com.example.bank.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @GetMapping("/get")
    public ResponseEntity getEmployee(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(employeeService.getEmployee(user.getId()));
    }
    @PostMapping("/add")
    public ResponseEntity addEmployee(@AuthenticationPrincipal User user, @RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.addEmployee(user.getId(),employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );
    }
    @PutMapping("/update/{employee_id}")
    public ResponseEntity update(@PathVariable Integer employee_id,@AuthenticationPrincipal User user,@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.update(user.getId(),employee_id,employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
    @DeleteMapping("/delete/{employee_id}")
    public ResponseEntity delete(@AuthenticationPrincipal User user,@PathVariable Integer employee_id) {
        employeeService.delete(user.getId(),employee_id);
        return ResponseEntity.status(200).body(new ApiResponse("Success") );

    }
}
