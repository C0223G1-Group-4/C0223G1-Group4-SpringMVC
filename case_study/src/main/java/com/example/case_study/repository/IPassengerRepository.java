package com.example.case_study.repository;

import com.example.case_study.model.Passengers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface IPassengerRepository extends JpaRepository<Passengers, Integer> {
    @Query(value = "select * from Passengers where name_passengers like %:name% ", nativeQuery = true)
    Page<Passengers> findByPassengers(@Param("name") String name, Pageable pageable);
    Optional<Passengers> findById(Integer id);
    Passengers findPassengersByAccountUser_Id(Integer id);

    Passengers findByAccountUser_Email(String email);

    Passengers findByVerificationCode(String code);

    Passengers findById(int id);
}
