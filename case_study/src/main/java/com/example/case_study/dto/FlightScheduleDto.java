package com.example.case_study.dto;


public class FlightScheduleDto {

    private Integer id;
    private String codeFlightSchedule;
    private String dateFlightSchedule;
    private String departure;
    private String arrival;
    private boolean flag;


    public FlightScheduleDto() {
    }

    public FlightScheduleDto(Integer id, String dateFlightSchedule, String departure, String arrival, boolean flag) {
        this.id = id;
        this.dateFlightSchedule = dateFlightSchedule;
        this.departure = departure;
        this.arrival = arrival;
        this.flag = flag;

    }

    public FlightScheduleDto(Integer id, String codeFlightSchedule, String dateFlightSchedule, String departure, String arrival, boolean flag) {
        this.id = id;
        this.codeFlightSchedule = codeFlightSchedule;
        this.dateFlightSchedule = dateFlightSchedule;
        this.departure = departure;
        this.arrival = arrival;
        this.flag = flag;
    }

    public FlightScheduleDto(String dateFlightSchedule, String departure, String arrival, boolean flag) {
        this.dateFlightSchedule = dateFlightSchedule;
        this.departure = departure;
        this.arrival = arrival;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateFlightSchedule() {
        return dateFlightSchedule;
    }

    public void setDateFlightSchedule(String dateFlightSchedule) {
        this.dateFlightSchedule = dateFlightSchedule;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getCodeFlightSchedule() {
        return codeFlightSchedule;
    }

    public void setCodeFlightSchedule(String codeFlightSchedule) {
        this.codeFlightSchedule = codeFlightSchedule;
    }
}
