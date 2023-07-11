package com.example.case_study.dto;

import com.example.case_study.model.RoleUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToOne;
import javax.validation.constraints.*;

public class AccountUserDto {
    private Integer id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Min(value = 6,message = "Password must be at least 6 characters")
    @Pattern(regexp = "^[\\\\w]+$",message = "Password does not contain special characters")
    private String passwords;
    private RoleUser roleUser;
    private boolean flagDelete;

    public AccountUserDto() {
    }

    public AccountUserDto(String email, String passwords) {
        this.email = email;
        this.passwords = passwords;
    }

    public AccountUserDto(Integer id, String email, String passwords, RoleUser roleUser, boolean flagDelete) {
        this.id = id;
        this.email = email;
        this.passwords = passwords;
        this.roleUser = roleUser;
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

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }
}
