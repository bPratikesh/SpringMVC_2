package com.example.SpringMVCweek2.SpringMVC.controllers;

import com.example.SpringMVCweek2.SpringMVC.dto.EmployeeDTO;
import com.example.SpringMVCweek2.SpringMVC.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/emp")
public class EmployeeController {

//    @GetMapping(path = "/msg")
//    public String secretMsg(){
//        return "Hey, How are you?";
//    }

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> getEmpById(@PathVariable Long empId){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(empId);
        return employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                             @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO inputInfoOfEmployee){
        EmployeeDTO savedEmp = employeeService.createEmp(inputInfoOfEmployee);
        return new ResponseEntity<>(savedEmp, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long empId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(empId, employeeDTO));
    }

    @DeleteMapping(path = "/{empId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long empId){
        boolean gotDeleted = employeeService.deleteEmpById(empId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmpById(@RequestBody Map<String, Object> updates,
                                            @PathVariable Long empId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(empId, updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }



}

