package com.example.case_study.repository;

import com.example.case_study.model.tai.FlightScheduleAirCraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFlightScheduleAirCraftRepository extends JpaRepository< FlightScheduleAirCraft ,Integer> {
    Page<FlightScheduleAirCraft> getFlightScheduleAirCraftsByFlagIsFalse(Pageable pageable);
//    @Query(value = "select * from flight_schedule_aircraft where air_craft_id = :id",nativeQuery = true)
//    List<FlightScheduleAirCraft> getAllAirCraft(@Param("id") int id);
    List<FlightScheduleAirCraft> getFlightScheduleAirCraftById(int id);

}
