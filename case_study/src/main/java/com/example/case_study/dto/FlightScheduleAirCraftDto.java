package com.example.case_study.dto;

import com.example.case_study.model.tai.AirCraft;
import com.example.case_study.model.tai.FlightSchedule;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

public class FlightScheduleAirCraftDto {
    private Integer id;
    @Pattern(regexp = "^BK-[0-9]{1,5}$",message = "Code ticket format BK- and 1 to 5 number")
    private String codeBooking;
    private FlightSchedule flightSchedule;
    private AirCraft idAirCraft;

    public FlightScheduleAirCraftDto() {
    }

    public FlightScheduleAirCraftDto(Integer id, FlightSchedule flightSchedule, AirCraft idAirCraft) {
        this.id = id;
        this.flightSchedule = flightSchedule;
        this.idAirCraft = idAirCraft;
    }

    public FlightScheduleAirCraftDto(Integer id, String codeBooking, FlightSchedule flightSchedule, AirCraft idAirCraft) {
        this.id = id;
        this.codeBooking = codeBooking;
        this.flightSchedule = flightSchedule;
        this.idAirCraft = idAirCraft;
    }

    public Integer getId() {        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FlightSchedule getFlightSchedule() {
        return flightSchedule;
    }

    public void setFlightSchedule(FlightSchedule flightSchedule) {
        this.flightSchedule = flightSchedule;
    }

    public AirCraft getIdAirCraft() {
        return idAirCraft;
    }

    public void setIdAirCraft(AirCraft idAirCraft) {
        this.idAirCraft = idAirCraft;
    }

    public String getCodeBooking() {
        return codeBooking;
    }

    public void setCodeBooking(String codeBooking) {
        this.codeBooking = codeBooking;
    }
}
