package com.example.case_study.dto;

import com.example.case_study.model.AccountUser;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class EmployeeDto {

    private Integer id;
    @NotBlank
    @Pattern(regexp = "^[\\w ]+$", message = "name does not contain special characters")
    private String nameEmployee;
    @NotBlank
    private String address;
    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Must be 10 numbers and contain no other characters")
    private String telephone;
    private AccountUser accountUser;
    private boolean flagDelete;
}
