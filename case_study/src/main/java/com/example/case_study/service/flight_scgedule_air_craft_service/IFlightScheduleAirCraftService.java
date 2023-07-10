package com.example.case_study.service.flight_scgedule_air_craft_service;

import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.model.tai.FlightScheduleAirCraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFlightScheduleAirCraftService {
    Page<FlightScheduleAirCraft> getAllList(Pageable pageable);
    List<FlightScheduleAirCraft> checkAllListFlightScheduleAirCraft();

    void createFlightScheduleAirCraft(FlightScheduleAirCraft flightScheduleAirCraft);
    void editFlightScheduleAirCraft(FlightScheduleAirCraft flightScheduleAirCraft);
    void deleteFlightScheduleAirCraft(FlightScheduleAirCraft flightScheduleAirCraft);

    FlightScheduleAirCraft findByIdFlightScheduleAirCraft(int id);
    List<FlightScheduleAirCraft> searchTicket(String airPort,String departure,String arrival,String destination);
}
