package com.example.case_study.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RouteDto {

    private Integer id;
    @NotBlank(message = "Air Port must not be left blank")
    private String airPort;
    @NotBlank(message = "Destination must not be left blank")
    private String destination;
    @Pattern(regexp = "^(R-)[0-9]{1,5}",message = "Code route format R- and 1 to 5 number")
    private String codeRoute;
    @Min(value = 1,message = "Fare greater than 1")
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
