package com.example.case_study.service.flight_schedule_service;

import com.example.case_study.model.tai.FlightSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFlightScheduleService {
    Page<FlightSchedule> getAllListFlightSchedule(Pageable pageable);
    List<FlightSchedule> checkAllListFlightSchedule();

    boolean createFlightSchedule(FlightSchedule flightSchedule);
    boolean editFlightSchedule(FlightSchedule flightSchedule);
    void deleteFlightSchedule(FlightSchedule flightSchedule);

    FlightSchedule findByIdFlightSchedule(int id);;
}
