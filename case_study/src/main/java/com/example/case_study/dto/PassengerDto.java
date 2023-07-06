package com.example.case_study.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PassengerDto implements Validator {
    private Integer id;
    @NotBlank(message = "Citizen ID cannot be left blank!")
//    @Pattern(regexp = "^[0-9]{12}$")
    private String citizenId;
    @NotBlank(message = "Customer name cannot be blank, minimum length is 2 and maximum is 45!")
    @Size(min = 2 , max = 45)
//    @Pattern(regexp = "^[\\\\p{Lu}][\\\\p{Ll}]*([\\\\s][\\\\p{Lu}][\\\\p{Ll}]*)*$", message = "Sai định dạng")
    private String name;
    @NotBlank(message = "Address cannot be empty!")
    private String address;
    @NotBlank(message = "Phone number cannot be blank, minimum length is 10 and maximum is 12!")
    @Size(min = 10, max = 12)
//    @Pattern(regexp = "^0\\\\d{10,12}$")
    private String phoneNumber;
    @NotNull(message = "Age cannot be empty!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String age;
    @NotBlank(message = "Nationality cannot be blank!")
    private String nationality;

    public PassengerDto() {
    }

    public PassengerDto(Integer id, String citizenId, String name, String address, String phoneNumber, String age, String nationality) {
        this.id = id;
        this.citizenId = citizenId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.nationality = nationality;
    }

    public PassengerDto(String citizenId, String name, String address, String phoneNumber, String age, String nationality) {
        this.citizenId = citizenId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.nationality = nationality;
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


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        PassengerDto passengersDto = (PassengerDto) target;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate dOB = LocalDate.parse(passengersDto.getAge(), formatter);
            LocalDate now = LocalDate.now();
            int yearOld = Period.between(dOB,now).getYears();
            if (yearOld >= 80){
                errors.rejectValue("dOB", "","Over the age limit to join the flight, the specified age is less than 80 years old!");
            }
        }catch (DateTimeParseException e){
            errors.rejectValue("dOB","dOB", "Wrong date format!");
        }

    }
}
