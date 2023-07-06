package com.example.case_study.service.account;

import com.example.case_study.model.AccountUser;
import com.example.case_study.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private IAccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountUser accountUser = this.accountRepository.findAccountUserByEmail(email);
        if (accountUser == null){
            System.out.println("User email not found " + email);
            throw new UsernameNotFoundException("User " +email +" was not found in the database");
        }
        System.out.println("Found User: " + email);
        List<AccountUser> userList = this.accountRepository.findByEmail(email);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (userList != null){
            for (AccountUser user : userList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(user.getRoleUser().getName());
                authorityList.add(authority);
            }
        }
        return (UserDetails) new User(accountUser.getEmail(),accountUser.getPasswords(),authorityList);
    }

}
