package com.example.case_study.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ChairFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChairFlight;
    private boolean statusChair;
    @OneToMany
    private List<Chair> chairList;

    public ChairFlight() {
    }

    public ChairFlight(int idChairFlight, boolean statusChair, List<Chair> chairList) {
        this.idChairFlight = idChairFlight;
        this.statusChair = statusChair;
        this.chairList = chairList;
    }

    public int getIdChairFlight() {
        return idChairFlight;
    }

    public void setIdChairFlight(int idChairFlight) {
        this.idChairFlight = idChairFlight;
    }

    public boolean isStatusChair() {
        return statusChair;
    }

    public void setStatusChair(boolean statusChair) {
        this.statusChair = statusChair;
    }

    public List<Chair> getChairList() {
        return chairList;
    }

    public void setChairList(List<Chair> chairList) {
        this.chairList = chairList;
    }
}
