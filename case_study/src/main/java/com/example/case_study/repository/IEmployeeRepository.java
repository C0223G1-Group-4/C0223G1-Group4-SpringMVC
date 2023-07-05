package com.example.case_study.repository;

import com.example.case_study.model.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employees, Integer> {
    @Query(value = "select * from Employees where name_employee like %:name% ", nativeQuery = true)
    Page<Employees> findByEmployees(@Param("name") String name, Pageable pageable);
    Optional<Employees> findById(Integer id);

}
