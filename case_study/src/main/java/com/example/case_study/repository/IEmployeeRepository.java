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

    Optional<Employees> findEmployeesById(Integer id);
    Employees findEmployeesByAccountUser_Id(Integer id);
    @Query(value = "select employees.address,employees.age,employees.name_employee,employees.telephone,account_user.email from employees join account_user on employees.account_user_id = account_user.id where account_user.email like concat('%', :email, '%') or employees.name_employee like concat('%', :name,'%')  order by employees.name_employee", nativeQuery = true)
    Page<Employees> findByEmployee(@Param("email") String email,
            @Param("name") String name, Pageable pageable);
}
