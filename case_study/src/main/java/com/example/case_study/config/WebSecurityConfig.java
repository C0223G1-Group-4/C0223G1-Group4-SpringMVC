package com.example.case_study.config;

import com.example.case_study.service.account.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private DataSource dataSource;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

    @Autowired
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers( "/","/login").permitAll();
        http.authorizeRequests().antMatchers("/").access("hasAnyRole('ROLE_Employee','ROLE_Customer','ROLE_Admin')");
        http.authorizeRequests().antMatchers("/passenger","/passenger/*").access("hasRole('ROLE_Employee')");
        http.authorizeRequests().antMatchers("/employee","/employee/*").access("hasRole('ROLE_Admin')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/400");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check") // submit url
                .loginPage("/login")
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("email")//
                .passwordParameter("passwords")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/logoutSuccessful");
        // Cấu hình Remember Me.
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(24 * 60 * 60); // 24h
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
}
