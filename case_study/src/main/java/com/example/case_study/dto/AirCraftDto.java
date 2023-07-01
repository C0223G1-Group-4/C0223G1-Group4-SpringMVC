package com.example.case_study.dto;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AirCraftDto {
    @Id
    private Integer idAirCraft;
    private String numberAirCraft;
    private Integer capacity;
}
