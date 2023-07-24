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
    List<FlightScheduleAirCraft> getFlightScheduleAirCraftById(int id);

    @Query(value = "SELECT * FROM flight_schedule_aircraft fsa \n" +
            "JOIN flight_schedule fs ON fsa.flight_schedule_id=fs.id \n" +
            "JOIN air_craft ac ON fsa.air_craft_id=ac.id \n" +
            "JOIN route_air_craft rac ON ac.id=rac.air_craft_id \n" +
            "JOIN route r ON rac.route_id=r.id \n" +
            "WHERE fs.arrival LIKE CONCAT('%',:arrival, '%') \n" +
            "AND fs.departure >= CURDATE() " +
            "AND MONTH(fs.departure) >= MONTH(current_date()) \n" +
            "AND YEAR(fs.departure) = YEAR(current_date()) \n" +
            "AND r.destination LIKE CONCAT('%', :destination, '%') \n" +
            "AND r.air_port LIKE CONCAT('%', :airPort, '%') \n" +
            "AND ac.flag=false AND r.flag=false AND fs.flag=false ",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket(@Param("airPort")String airPort
            ,@Param("arrival") String arrival,@Param("destination") String destination);

    @Query(value = "  SELECT * FROM flight_schedule_aircraft fsa JOIN flight_schedule fs ON fsa.flight_schedule_id = fs.id\n" +
            " JOIN air_craft ac ON fsa.air_craft_id = ac.id JOIN route_air_craft rac ON ac.id = rac.air_craft_id \n" +
            " JOIN route r ON rac.route_id = r.id \n" +
            "WHERE fs.arrival BETWEEN NOW() AND  :arrival" +
            " and fs.departure > CURDATE() " +
            " and r.destination like concat('%', :destination, '%') and r.air_port like concat('%',:airPort, '%') \n" +
            "AND ac.flag = FALSE AND r.flag = FALSE AND fs.flag = FALSE",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket1(@Param("airPort")String airPort,@Param("arrival") String arrival,@Param("destination") String destination );


    @Query(value = "select * from flight_schedule_aircraft fsa join flight_schedule fs " +
            "on fsa.flight_schedule_id=fs.id join air_craft ac on fsa.air_craft_id=ac.id " +
            "join route_air_craft rac on ac.id=rac.air_craft_id join route r on rac.route_id=r.id where  fs.arrival " +
            "like concat('%',:arrival, '%') and STR_TO_DATE(fs.departure, '%Y-%m-%d') >= STR_TO_DATE(:departure, '%Y-%m-%d') and month(fs.departure) >= month(current_date()) and year(fs.departure)=year(current_date())" +
            " and r.destination like concat('%', :destination, '%') and r.air_port like concat('%',:airPort, '%')" +
            "and ac.flag=false and r.flag=false and fs.flag=false ",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket2(@Param("airPort")String airPort,@Param("departure") String departure
            ,@Param("arrival") String arrival,@Param("destination") String destination);
           @Query(value = "select * from flight_schedule_aircraft fsa join flight_schedule fs " +
            "on fsa.flight_schedule_id=fs.id join air_craft ac on fsa.air_craft_id=ac.id " +
            "join route_air_craft rac on ac.id=rac.air_craft_id join route r on rac.route_id=r.id where  fs.arrival " +
            "like concat('%', :arrival,'%') and fs.departure >= current_date() and month(fs.departure) >= month(current_date()) and year(fs.departure)=year(current_date()) and r.destination " +
            " like concat('%', :destination,'%') and r.air_port like concat('%', :airPort,'%')" +
            "and ac.flag=false and r.flag=false and fs.flag=false ",nativeQuery = true)
    List<FlightScheduleAirCraft> searchTicket4(@Param("airPort")String airPort
            ,@Param("arrival") String arrival,@Param("destination") String destination);


    @Query(value = "SELECT * FROM flight_schedule_aircraft fsa\n" +
            "JOIN flight_schedule fs ON fsa.flight_schedule_id = fs.id\n" +
            "JOIN air_craft ac ON fsa.air_craft_id = ac.id\n" +
            "JOIN route_air_craft rac ON ac.id = rac.air_craft_id \n" +
            "JOIN route r ON rac.route_id = r.id \n" +
            " WHERE fs.arrival BETWEEN :departure AND :arrival \n" +
            " AND fs.departure BETWEEN :departure AND :arrival" +
            " and r.destination like concat('%',:destination,'%') and r.air_port like concat('%',:airPort,'%') \n" +
            "AND ac.flag = FALSE AND r.flag = FALSE AND fs.flag = FALSE ",nativeQuery = true)
        List<FlightScheduleAirCraft> searchTicket3(@Param("airPort")String airPort,@Param("departure") String departure
                ,@Param("arrival") String arrival,@Param("destination") String destination);

}
