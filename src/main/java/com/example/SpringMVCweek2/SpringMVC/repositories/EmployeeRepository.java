package com.example.SpringMVCweek2.SpringMVC.repositories;

import com.example.SpringMVCweek2.SpringMVC.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
