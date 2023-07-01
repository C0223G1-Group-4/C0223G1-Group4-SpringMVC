package com.example.case_study.repository;

import com.example.case_study.model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightScheduleRepository extends JpaRepository<Integer, FlightSchedule> {
}
