package com.example.SpringMVCweek2.SpringMVC.controllers;

import com.example.SpringMVCweek2.SpringMVC.dto.EmployeeDTO;
import com.example.SpringMVCweek2.SpringMVC.entities.EmployeeEntity;
import com.example.SpringMVCweek2.SpringMVC.repositories.EmployeeRepository;
import com.example.SpringMVCweek2.SpringMVC.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public EmployeeDTO getEmpById(@PathVariable Long empId){
        return employeeService.getEmployeeById(empId);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                             @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputInfoOfEmployee){
        return employeeService.createEmp(inputInfoOfEmployee);
    }

    @PutMapping(path = "/{empId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long empId){
        return employeeService.updateEmployeeById(empId, employeeDTO);
    }

    @DeleteMapping(path = "/{empId}")
    public boolean deleteEmployeeById(@PathVariable Long empId){
        return employeeService.deleteEmpById(empId);
    }

    @PatchMapping(path = "/{empId}")
    public EmployeeDTO updatePartialEmpById(@RequestBody Map<String, Object> updates, @PathVariable Long empId){
        return employeeService.updatePartialEmployeeById(empId, updates);
    }



}

