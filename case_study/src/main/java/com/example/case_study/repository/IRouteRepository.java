package com.example.case_study.repository;

import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.model.tai.FlightSchedule;
import com.example.case_study.model.tai.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRouteRepository extends JpaRepository<Route,Integer> {
    Page<Route> findFlightScheduleByFlagFalse(Pageable pageable);
    List<Route> getFlightScheduleByFlagFalse();
}
