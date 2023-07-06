package com.example.case_study.model;

import javax.persistence.*;

@Entity
public class Passengers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String citizenId;
    @Column(name = "name_passengers")
    private String name;
    private String address;
    private String phoneNumber;
    private String age;
    private String nationality;
    @ManyToOne
    private AccountUser accountUser;
    @Column(columnDefinition = "bit default 0")
    private boolean flagDelete;

    public Passengers() {
    }

    public Passengers(Integer id, String citizenId, String name, String address, String phoneNumber, String age, String nationality, AccountUser accountUser, boolean flagDelete) {
        this.id = id;
        this.citizenId = citizenId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.nationality = nationality;
        this.accountUser = accountUser;
        this.flagDelete = flagDelete;
    }

    public Passengers(Integer id, String citizenId, String name, String address, String phoneNumber, String age, String nationality, AccountUser accountUser) {
        this.id = id;
        this.citizenId = citizenId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.nationality = nationality;
        this.accountUser = accountUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(String citizenId) {
        this.citizenId = citizenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}
