package com.example.case_study.service.passengers_service;

import com.example.case_study.model.Passengers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IPassengersService {
    Page<Passengers> findByPassengers(String name, Pageable pageable);
    Optional<Passengers> findByIdPassengers(Integer id);
    void create(Passengers passengers);
    void update(Passengers passengers);
    void delete(Integer id);
    Passengers findByIdAccount(Integer id);

}
