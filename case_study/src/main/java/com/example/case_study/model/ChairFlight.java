package com.example.case_study.model;

import javax.persistence.*;

@Entity
public class ChairFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean statusChair;
    @ManyToOne
    @JoinColumn(name = "id_chair")
    private Chair chair;

    @ManyToOne
    @JoinColumn(name ="id_flight_schedule_aircraft")
    private FlightScheduleAirCraft flightScheduleAirCraft;

    @ManyToOne
    @JoinColumn(name = "id_bk")
    private BookingTicket bookingTicket;

    public ChairFlight(int id, boolean statusChair, Chair chair,
                       FlightScheduleAirCraft flightScheduleAirCraft) {
        this.id = id;
        this.statusChair = statusChair;
        this.chair = chair;
        this.flightScheduleAirCraft = flightScheduleAirCraft;
    }

    public ChairFlight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int idChairFlight) {
        this.id = idChairFlight;
    }

    public boolean isStatusChair() {
        return statusChair;
    }

    public void setStatusChair(boolean statusChair) {
        this.statusChair = statusChair;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public FlightScheduleAirCraft getFlightScheduleAirCraft() {
        return flightScheduleAirCraft;
    }

    public void setFlightScheduleAirCraft(FlightScheduleAirCraft flightScheduleAirCraft) {
        this.flightScheduleAirCraft = flightScheduleAirCraft;
    }
}
