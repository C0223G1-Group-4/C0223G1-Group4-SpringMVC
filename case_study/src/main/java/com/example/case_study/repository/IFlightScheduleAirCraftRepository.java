package com.example.case_study.repository;

import com.example.case_study.model.FlightScheduleAirCraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFlightScheduleAirCraftRepository extends JpaRepository< FlightScheduleAirCraft ,Integer> {
}
