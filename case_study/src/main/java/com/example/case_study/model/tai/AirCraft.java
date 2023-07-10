package com.example.case_study.model.tai;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="air_craft")
public class AirCraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String numberAirCraft;
    private Integer capacity;
    private boolean flag;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "route_air_craft",
            joinColumns = @JoinColumn(name = "air_craft_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    @JsonBackReference
    private List<Route> routes;

    public AirCraft() {
    }

    public AirCraft(Integer id, String numberAirCraft, Integer capacity, boolean flag, List<Route> routes) {
        this.id = id;
        this.numberAirCraft = numberAirCraft;
        this.capacity = capacity;
        this.flag = flag;
        this.routes = routes;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer idAirCraft) {
        this.id = idAirCraft;
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

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

}
