package com.example.case_study.service.flight_schedule_service;

import com.example.case_study.model.AirCraft;
import com.example.case_study.model.FlightSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IFlightScheduleService {
    Page<FlightSchedule> getAllListFlightSchedule(Pageable pageable);

    void createFlightSchedule(FlightSchedule flightSchedule);
    void editFlightSchedule(FlightSchedule flightSchedule);
    void deleteFlightSchedule(FlightSchedule flightSchedule);

    FlightSchedule findByIdFlightSchedule(int id);;
}
