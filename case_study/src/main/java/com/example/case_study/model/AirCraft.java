package com.example.case_study.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "air_craft")
public class AirCraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAirCraft;
    private String numberAirCraft;
    private Integer capacity;
    private boolean flag;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "route_air_craft",
            joinColumns = @JoinColumn(name = "id_aircraft"),
            inverseJoinColumns = @JoinColumn(name = "id_route")
    )
    private List<Route> routes;
}
