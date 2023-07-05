package com.example.case_study.service.account;

import com.example.case_study.model.AccountUser;

public interface IAccountService {

    AccountUser findByEmail(AccountUser accountUser);
}
