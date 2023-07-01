package com.example.case_study.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ChairFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChairFlight;
    private boolean statusChair;
    @ManyToOne
    @JoinColumn(name = "id_chair")
    private Chair chair;

    public ChairFlight(int idChairFlight, boolean statusChair, Chair chair) {
        this.idChairFlight = idChairFlight;
        this.statusChair = statusChair;
        this.chair = chair;
    }

    public ChairFlight() {
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

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }
}
