package com.example.case_study.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameChair;

    public Chair() {
    }

    public Chair(int id, String nameChair) {
        this.id = id;
        this.nameChair = nameChair;
    }

    public int getId() {
        return id;
    }

    public void setId(int idChair) {
        this.id = idChair;
    }

    public String getNameChair() {
        return nameChair;
    }

    public void setNameChair(String nameChair) {
        this.nameChair = nameChair;
    }
}
