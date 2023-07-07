package com.example.case_study.model.tai;

import javax.persistence.*;

@Entity
public class FlightSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codeFlightSchedule;
    private String departure;
    private String arrival;
    private boolean flag;


    public FlightSchedule() {
    }


    public FlightSchedule(Integer id, String codeFlightSchedule, String departure, String arrival, boolean flag) {
        this.id = id;
        this.codeFlightSchedule = codeFlightSchedule;
        this.departure = departure;
        this.arrival = arrival;
        this.flag = flag;
    }

    public FlightSchedule(String departure, String arrival, boolean flag) {

        this.departure = departure;
        this.arrival = arrival;
        this.flag = flag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idFlightSchedule) {
        this.id = idFlightSchedule;
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
