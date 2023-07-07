package com.example.case_study.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class FlightScheduleDto {

    private Integer id;
//    @Pattern(regexp = "^(FS-)[0-9]{1,5}$",message = "Code Flight Schedule Format FS- and 1 to 5 number")
    private String codeFlightSchedule;
    @NotBlank(message = "Departure must not be left blank")
    private String departure;
    @NotBlank(message = "Arrival must not be left blank")
    private String arrival;
    private boolean flag;


    public FlightScheduleDto() {
    }

    public FlightScheduleDto(Integer id, String departure, String arrival, boolean flag) {
        this.id = id;

        this.departure = departure;
        this.arrival = arrival;
        this.flag = flag;

    }

    public FlightScheduleDto(Integer id, String codeFlightSchedule, String departure, String arrival, boolean flag) {
        this.id = id;
        this.codeFlightSchedule = codeFlightSchedule;

        this.departure = departure;
        this.arrival = arrival;
        this.flag = flag;
    }

    public FlightScheduleDto( String departure, String arrival, boolean flag) {

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
