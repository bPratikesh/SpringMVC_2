package com.example.SpringMVCweek2.SpringMVC.services;

import com.example.SpringMVCweek2.SpringMVC.dto.EmployeeDTO;
import com.example.SpringMVCweek2.SpringMVC.entities.EmployeeEntity;
import com.example.SpringMVCweek2.SpringMVC.exception.ResourceNotFoundException;
import com.example.SpringMVCweek2.SpringMVC.repositories.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
        //EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        //return modelMapper.map(employeeEntity,EmployeeDTO.class);

        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmp(EmployeeDTO inputInfoOfEmployee) {
        EmployeeEntity toSaveEntityFormDTO = modelMapper.map(inputInfoOfEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntityFormDTO);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }


    public EmployeeDTO updateEmployeeById(Long empId, EmployeeDTO employeeDTO) {
        isExistsByEmpId(empId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(empId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public void isExistsByEmpId(Long empId){
        boolean exists = employeeRepository.existsById(empId);
        if(!exists) throw new ResourceNotFoundException("Emolyee not found with id:"+empId);
    }

    public boolean deleteEmpById(Long empId) {
        isExistsByEmpId(empId);
        employeeRepository.deleteById(empId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long empId, Map<String, Object> updates) {
        isExistsByEmpId(empId);
        EmployeeEntity employeeEntity = employeeRepository.findById(empId).get();
        updates.forEach((field, value)->{
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
