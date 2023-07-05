package com.example.case_study.service.flight_scgedule_air_craft_service;

import com.example.case_study.model.AirCraft;
import com.example.case_study.model.FlightScheduleAirCraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFlightScheduleAirCraftService {
    Page<FlightScheduleAirCraft> getAllList(Pageable pageable);
}
