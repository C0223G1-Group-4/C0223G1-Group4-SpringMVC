package com.example.case_study.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRoute;
    private String airPort;
    private String destination;
    private String codeRoute;
    private Float fare;
    private boolean flag;

    @ManyToMany
    @JoinTable(
            name = "route_air_craft",
            joinColumns = @JoinColumn(name = "id_route"),
            inverseJoinColumns = @JoinColumn(name = "id_aircraft")
    )
    private List<AirCraft> airCrafts;
}
