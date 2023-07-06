package com.example.case_study.repository;

import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.model.tai.FlightSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFlightScheduleRepository extends JpaRepository< FlightSchedule,Integer> {
    Page<FlightSchedule> findFlightScheduleByFlagFalse(Pageable pageable);
    List<FlightSchedule> getAirCraftByFlagIsFalse();
}
