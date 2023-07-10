package com.example.case_study.repository;

import com.example.case_study.model.tai.FlightScheduleAirCraft;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IFlightScheduleAirCraftRepository extends JpaRepository< FlightScheduleAirCraft ,Integer> {
    Page<FlightScheduleAirCraft> getFlightScheduleAirCraftsByFlagIsFalse(Pageable pageable);
    List<FlightScheduleAirCraft> getFlightScheduleAirCraftById(int id);
    @Query(value = "select * from flight_schedule_aircraft fsa join flight_schedule fs " +
            "on fsa.flight_schedule_id=fs.id join air_craft ac on fsa.air_craft_id=ac.id " +
            "join route_air_craft rac on ac.id=rac.air_craft_id join route r on rac.route_id=r.id where  fs.arrival " +
            "like concat('%',:arrival,'%') and fs.departure like concat('%',:departure,'%') and r.destination" +
            " like concat('%',:destination,'%') and r.air_port like concat('%',:airPort,'%')" +
            "and ac.flag=false and r.flag=false and fs.flag=false ",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket(@Param("airPort")String airPort,@Param("departure") String departure
            ,@Param("arrival") String arrival,@Param("destination") String destination);
      @Query(value = "select * from flight_schedule_aircraft fsa join flight_schedule fs " +
            "on fsa.flight_schedule_id=fs.id join air_craft ac on fsa.air_craft_id=ac.id " +
            "join route_air_craft rac on ac.id=rac.air_craft_id join route r on rac.route_id=r.id where  " +
            " day(fs.arrival) <= day(:arrival) and fs.departure like concat('%',:departure,'%') and r.destination" +
            " like concat('%',:destination,'%') and r.air_port like concat('%',:airPort,'%')" +
            "and ac.flag=false and r.flag=false and fs.flag=false ",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket1(@Param("airPort")String airPort,@Param("departure") String departure
            ,@Param("arrival") String arrival,@Param("destination") String destination);
      @Query(value = "select * from flight_schedule_aircraft fsa join flight_schedule fs " +
            "on fsa.flight_schedule_id=fs.id join air_craft ac on fsa.air_craft_id=ac.id " +
            "join route_air_craft rac on ac.id=rac.air_craft_id join route r on rac.route_id=r.id where  fs.arrival " +
            "like concat('%',:arrival,'%') and day(fs.departure) >= day(:departure) and r.destination " +
            " like concat('%',:destination,'%') and r.air_port like concat('%',:airPort,'%')" +
            "and ac.flag=false and r.flag=false and fs.flag=false ",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket2(@Param("airPort")String airPort,@Param("departure") String departure
            ,@Param("arrival") String arrival,@Param("destination") String destination);
@Query(value = "select * from flight_schedule_aircraft fsa join flight_schedule fs " +
            "on fsa.flight_schedule_id=fs.id join air_craft ac on fsa.air_craft_id=ac.id " +
            "join route_air_craft rac on ac.id=rac.air_craft_id join route r on rac.route_id=r.id where  day(fs.arrival) " +
            " <= day(:arrival) and day(fs.departure) >= day(:departure) and r.destination " +
            " like concat('%',:destination,'%') and r.air_port like concat('%',:airPort,'%')" +
            "and ac.flag=false and r.flag=false and fs.flag=false ",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket3(@Param("airPort")String airPort,@Param("departure") String departure
            ,@Param("arrival") String arrival,@Param("destination") String destination);
    FlightScheduleAirCraft findById(int id);
}
