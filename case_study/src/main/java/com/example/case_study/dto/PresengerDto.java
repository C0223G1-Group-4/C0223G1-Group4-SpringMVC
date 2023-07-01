package com.example.case_study.dto;

import com.example.case_study.model.AccountUser;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PresengerDto {
    private Integer id;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10,12}$",message = "Must be between 10 and 12 numbers and contain no other characters")
    private String citizenId;
    @NotBlank
    @Pattern(regexp = "^[\\w ]+$", message = "name does not contain special characters")
    private String name;
    private String address;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Must be 10 numbers and contain no other characters")
    private String phoneNumber;
    @Min(1)
    private Integer age;

    private String nationality;
    private AccountUser accountUser;
    private boolean flagDelete;

    public PresengerDto() {
    }

    public PresengerDto(Integer id, String citizenId, String name, String address, String phoneNumber, Integer age, String nationality, AccountUser accountUser, boolean flagDelete) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
