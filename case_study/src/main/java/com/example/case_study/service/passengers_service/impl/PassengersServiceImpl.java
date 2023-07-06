package com.example.case_study.service.passengers_service.impl;

import com.example.case_study.model.Passengers;
import com.example.case_study.repository.IPassengerRepository;
import com.example.case_study.service.passengers_service.IPassengersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengersServiceImpl implements IPassengersService {
    @Autowired
    private IPassengerRepository iPassengerRepository;
    @Override
    public Page<Passengers> findByPassengers(String name, Pageable pageable) {
        return iPassengerRepository.findByPassengers(name, pageable);
    }

    @Override
    public Passengers findByIdPassengers(Integer id) {
        return iPassengerRepository.findById(id).get();
    }

    @Override
    public void create(Passengers passengers) {
        iPassengerRepository.save(passengers);
    }

    @Override
    public void update(Passengers passengers) {
        iPassengerRepository.save(passengers);
    }

    @Override
    public void delete(Integer id) {
        iPassengerRepository.deleteById(id);
    }
}
