package com.example.case_study.repository;

import com.example.case_study.model.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<AccountUser,Integer>{

}
