package com.example.case_study.dto;

import com.example.case_study.model.tai.Route;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;


public class AirCraftDto {

    private Integer id;
    @Pattern(regexp = "^(AC-)[0-9]{1,5}$",message = "Number Air Craft Format AC- and 1 to 5 number")
    private String numberAirCraft;
    @Min(value = 1,message = "Capacity need greater than 1")
    private Integer capacity;

    private List<Route> routes;
    public AirCraftDto() {
    }

    public AirCraftDto(Integer id, String numberAirCraft, Integer capacity) {
        this.id = id;
        this.numberAirCraft = numberAirCraft;
        this.capacity = capacity;
    }

    public AirCraftDto(Integer id, String numberAirCraft, Integer capacity, List<Route> routes) {
        this.id = id;
        this.numberAirCraft = numberAirCraft;
        this.capacity = capacity;
        this.routes = routes;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberAirCraft() {
        return numberAirCraft;
    }

    public void setNumberAirCraft(String numberAirCraft) {
        this.numberAirCraft = numberAirCraft;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
