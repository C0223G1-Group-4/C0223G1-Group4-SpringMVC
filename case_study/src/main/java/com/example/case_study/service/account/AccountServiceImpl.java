package com.example.case_study.service.account;

import com.example.case_study.model.AccountUser;
import com.example.case_study.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public AccountUser findByEmail(AccountUser accountUser) {
        if (accountRepository.findAccountUserByEmail(accountUser.getEmail()) == accountUser ){
           return accountRepository.findAccountUserByEmail(accountUser.getEmail());
        }
        return null;
    }
}
