package com.example.case_study.model;


import javax.persistence.*;

    @Entity
    @Table(name = "flight_schedule_aircraft")
    public class FlightScheduleAirCraft {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer idFlightScheduleAirCraft;
        @ManyToOne
        @JoinColumn(name = "id_flightSchedule")
        private FlightSchedule flightSchedule;
        @ManyToOne
        @JoinColumn(name = "id_aircraft")
        private AirCraft idAirCraft;
    }
