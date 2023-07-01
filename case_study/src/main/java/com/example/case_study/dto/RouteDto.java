package com.example.case_study.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class RouteDto {
    @Id
    private Integer idRoute;
    private String airPort;
    private String destination;
    private String codeRoute;
    private Float fare;
}
