package com.example.case_study.model;

import javax.persistence.*;

@Entity
public class RoleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(columnDefinition = "bit default 0")
    private boolean flagDelete;

    public RoleUser() {
    }

    public RoleUser(Integer id, String name, boolean flagDelete) {
        this.id = id;
        this.name = name;
        this.flagDelete = flagDelete;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
