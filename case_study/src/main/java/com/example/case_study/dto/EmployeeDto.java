package com.example.case_study.dto;

import com.example.case_study.model.AccountUser;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EmployeeDto implements Validator {

    private Integer id;
    @NotBlank(message = "Employees name cannot be blank!")
    @Size(min = 2 , max = 45)
    @Pattern(regexp = "^[\\\\p{Lu}][\\\\p{Ll}]*([\\\\s][\\\\p{Lu}][\\\\p{Ll}]*)*$")
    private String nameEmployee;
    @NotBlank(message = "Age cannot be empty!")
    private String age;
    @NotBlank(message = "Address cannot be empty!")
    private String address;
    @NotBlank(message = "Phone number cannot be blank, minimum length is 10 and maximum is 12!")
    @Size(min = 10, max = 12)
    @Pattern(regexp = "^0\\\\d{10,12}$")
    private String telephone;
//    private boolean flagDelete;

    public EmployeeDto(Integer id, String nameEmployee, String age, String address, String telephone) {
        this.id = id;
        this.nameEmployee = nameEmployee;
        this.age = age;
        this.address = address;
        this.telephone = telephone;
    }

    public EmployeeDto(String nameEmployee, String age, String address, String telephone) {
        this.nameEmployee = nameEmployee;
        this.age = age;
        this.address = address;
        this.telephone = telephone;
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


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeDto employeesDto = (EmployeeDto) target;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate age = LocalDate.parse(employeesDto.getAge(), formatter);
            LocalDate now = LocalDate.now();
            int yearOld = Period.between(age,now).getYears();
            if (yearOld >= 65 || yearOld < 18){
                errors.rejectValue("age", "You are not old enough or over the required age of the employee!");
            }
        }catch (DateTimeParseException e){
            errors.rejectValue("age","age", "Wrong date format!");
        }

    }
}
