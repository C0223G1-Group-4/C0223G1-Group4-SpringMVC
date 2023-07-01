package com.example.case_study.service.aircraft_service;

import com.example.case_study.model.AirCraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAirCraftService {
    Page<AirCraft> getAllListAirCraft(Pageable pageable);
    void createAirCraft(AirCraft airCraft);
    void editAirCraft(AirCraft airCraft);
    void deleteAirCraft(AirCraft airCraft);

    AirCraft findByIdAirCraft(int id);;
}
