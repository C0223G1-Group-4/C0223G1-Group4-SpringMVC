package com.example.case_study.repository;

import com.example.case_study.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAccountRepository extends JpaRepository<AccountUser,Integer>{
    AccountUser findAccountUserByEmail(String email);
    List<AccountUser> findByEmail(String email);

}
