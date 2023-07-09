package com.example.case_study.model.tai;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String airPort;
    private String destination;
    private String codeRoute;
    private Float fare;
    private boolean flag;
    @JsonBackReference
    @ManyToMany(mappedBy = "routes",fetch = FetchType.LAZY)
    private List<AirCraft> airCrafts;

    public Route() {
    }

    public Route(Integer id, String airPort, String destination, String codeRoute, Float fare, boolean flag, List<AirCraft> airCrafts) {
        this.id = id;
        this.airPort = airPort;
        this.destination = destination;
        this.codeRoute = codeRoute;
        this.fare = fare;
        this.flag = flag;
        this.airCrafts = airCrafts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer idRoute) {
        this.id = idRoute;
    }

    public String getAirPort() {
        return airPort;
    }

    public void setAirPort(String airPort) {
        this.airPort = airPort;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCodeRoute() {
        return codeRoute;
    }

    public void setCodeRoute(String codeRoute) {
        this.codeRoute = codeRoute;
    }

    public Float getFare() {
        return fare;
    }

    public void setFare(Float fare) {
        this.fare = fare;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<AirCraft> getAirCrafts() {
        return airCrafts;
    }

    public void setAirCrafts(List<AirCraft> airCrafts) {
        this.airCrafts = airCrafts;
    }
}
