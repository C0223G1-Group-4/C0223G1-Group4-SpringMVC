package com.example.case_study.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFlightSchedule;
    private LocalDateTime dateFlightSchedule;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private boolean flag;
    @OneToMany(mappedBy = "flight_schedule_aircraft")
    private List<FlightScheduleAirCraft> flightScheduleAirCrafts;


}
