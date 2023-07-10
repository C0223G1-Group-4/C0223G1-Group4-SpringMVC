package com.example.case_study.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(nullable = false)
    private String email;
    @JsonIgnore
    private String passwords;
    @ManyToOne
    private RoleUser roleUser;
    @OneToMany(mappedBy = "accountUser")
    private Set<Employees> employeesSet;

    @Column(columnDefinition = "bit default 0")
    private boolean flagDelete;

    public AccountUser() {
    }

    public AccountUser(Integer id, String email, String passwords, RoleUser roleUser, Set<Employees> employeesSet, boolean flagDelete) {
        this.id = id;
        this.email = email;
        this.passwords = passwords;
        this.roleUser = roleUser;
        this.employeesSet = employeesSet;
        this.flagDelete = flagDelete;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public Set<Employees> getEmployeesSet() {
        return employeesSet;
    }

    public void setEmployeesSet(Set<Employees> employeesSet) {
        this.employeesSet = employeesSet;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}
