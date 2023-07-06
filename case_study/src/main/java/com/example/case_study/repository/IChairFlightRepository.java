package com.example.case_study.repository;

import com.example.case_study.model.ChairFlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IChairFlightRepository extends JpaRepository<ChairFlight, Integer> {
        @Query(value = "select * from chair_flight where id_flight_schedule_aircraft = :num",nativeQuery = true)
        List<ChairFlight> getList(@Param("num") int num);
}
