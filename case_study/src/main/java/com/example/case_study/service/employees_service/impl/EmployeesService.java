package com.example.case_study.service.employees_service.impl;

import com.example.case_study.model.Employees;
import com.example.case_study.repository.IEmployeeRepository;
import com.example.case_study.service.employees_service.IEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService implements IEmployeesService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public List<Employees> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Page<Employees> findAll(String name, Pageable pageable) {
        return iEmployeeRepository.findByEmployees(name, pageable);
    }

    @Override
    public void create(Employees employees) {
        iEmployeeRepository.save(employees);
    }

    @Override
    public void update(Employees employees) {
        iEmployeeRepository.save(employees);
    }

    @Override
    public void delete(Integer id) {
        iEmployeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employees> findByIdEmployee(Integer id) {
        return iEmployeeRepository.findById(id);
    }
}
