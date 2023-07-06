package com.example.case_study.dto;

public class RouteDto {

    private Integer id;
    private String airPort;
    private String destination;
    private String codeRoute;
    private Float fare;

    public RouteDto() {
    }

    public RouteDto(Integer id, String airPort, String destination, String codeRoute, Float fare) {
        this.id = id;
        this.airPort = airPort;
        this.destination = destination;
        this.codeRoute = codeRoute;
        this.fare = fare;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


}
