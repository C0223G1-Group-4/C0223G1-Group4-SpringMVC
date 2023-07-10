package com.example.case_study.service.account;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.RoleUser;

import java.util.List;

public interface IAccountService {

    AccountUser findByEmail(String email);
    RoleUser findRoleById(int id);

    void createAccount(AccountUser accountUser);

    List<AccountUser> findAll();
}
