package com.example.case_study.repository;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.Employees;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<Employees, Integer> {
    @Query(value = "select * from Employees where name_employee like %:name% ", nativeQuery = true)
    Page<Employees> findByEmployees(@Param("name") String name, Pageable pageable);
    Optional<Employees> findById(Integer id);
    Employees findEmployeesByAccountUser_Id(Integer id);
    @Query(value = "select employee.id,employees.age,employees.name_employee,employees.telephone,account_user.email,employee.account_user_id from employees join account_user on employees.account_user_id=account_user.id where email like %:email%;", nativeQuery = true)
    List<Employees> findByAccount(@Param("email") String email);
}
