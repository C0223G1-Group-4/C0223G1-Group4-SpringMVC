package com.example.case_study.service.account;

import com.example.case_study.model.AccountUser;
import com.example.case_study.model.RoleUser;
import com.example.case_study.repository.IAccountRepository;
import com.example.case_study.repository.IRoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRoleUser roleUser;

    @Override
    public AccountUser findByEmail(String email) {
        if (accountRepository.findAccountUserByEmail(email) == null ){
            return null;
        }
        return accountRepository.findAccountUserByEmail(email);

    }

    @Override
    public RoleUser findRoleById(int id) {
        return roleUser.findById(id);
    }

    @Override
    public void createAccount(AccountUser accountUser) {
        accountRepository.save(accountUser);
    }

    @Override
    public List<AccountUser> findAll() {
        return accountRepository.findAll();
    }
}
