package com.example.case_study.service.employees_service;

import com.example.case_study.model.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IEmployeesService {
    Page<Employees> findAll(String name, Pageable pageable);
    void create(Employees employees);
    void update(Employees employees);
    void delete(Integer id);
    Optional<Employees> findByIdEmployee(Integer id);
    Employees findByIdAccount(Integer id);
    Employees findById(Integer id);
}
