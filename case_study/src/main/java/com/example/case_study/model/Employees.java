package com.example.case_study.model;

import javax.persistence.*;


@Entity
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameEmployee;
    private String age;

    private String address;
    private String telephone;
    @ManyToOne
    private AccountUser accountUser;
    @Column(columnDefinition = "bit default 0")
    private boolean flagDelete;

    public Employees() {
    }

    public Employees(Integer id, String nameEmployee, String age, String address, String telephone, AccountUser accountUser, boolean flagDelete) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.age = age;
        this.address = address;
        this.telephone = telephone;
        this.accountUser = accountUser;
        this.flagDelete = flagDelete;
    }

    public Employees(Integer id, String nameEmployee, String age, String address, String telephone, AccountUser accountUser) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.age = age;
        this.address = address;
        this.telephone = telephone;
        this.accountUser = accountUser;
    }

    public Employees(String nameEmployee, String age, String address, String telephone) {
        this.nameEmployee = nameEmployee;
        this.age = age;
        this.address = address;
        this.telephone = telephone;
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

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
