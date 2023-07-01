package com.example.case_study.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idChair;
    private String nameChair;

    public Chair() {
    }

    public Chair(int idChair, String nameChair) {
        this.idChair = idChair;
        this.nameChair = nameChair;
    }

    public int getIdChair() {
        return idChair;
    }

    public void setIdChair(int idChair) {
        this.idChair = idChair;
    }

    public String getNameChair() {
        return nameChair;
    }

    public void setNameChair(String nameChair) {
        this.nameChair = nameChair;
    }
}
