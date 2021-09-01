package com.poly;

import com.poly.entity.Account;
import com.poly.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.NoSuchElementException;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AccountService accountService;

    @Autowired
    BCryptPasswordEncoder pe;

    // Provide data resources for login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            try {
                Account user = accountService.findById(username);
                String password = pe.encode(user.getPassword());
                String[] roles = user.getAuthorities().stream()
                        .map(role -> role.getRole().getId()).toArray(String[]::new);
                return User.withUsername(username).password(password).roles(roles).build();
            }catch (NoSuchElementException e){
                throw new UsernameNotFoundException(username + " not found");
            }
        });
    }

    //Authorize
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
                .antMatchers("/rest/authorities").hasRole("DIRE")
                .antMatchers("/assets/admin/**").hasRole("DIRE")
                .anyRequest().permitAll();

        //Login form
        http.formLogin()
                .loginPage("/security/login/form")
                .loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/security/login/success",false)
                .failureUrl("/security/login/error");


        //Remember me?
        http.rememberMe()
                .tokenValiditySeconds(86400);

        //Exception handling
        http.exceptionHandling()
                .accessDeniedPage("/security/unauthorized");

        //Logout processing
        http.logout()
                .logoutUrl("/security/logoff")
                .logoutSuccessUrl("/security/logoff/success");


        //Login from Socials
        http.oauth2Login()
                .loginPage("/security/login/form")
                .defaultSuccessUrl("/oauth2/login/success",false)
                .failureUrl("/security/login/form")
                .authorizationEndpoint()
                .baseUri("/oauth2/authorization");
    }


    //Password encoding
    @Bean
    public BCryptPasswordEncoder getPe() {
        return new BCryptPasswordEncoder();
    }

    //Allowing accessing restAPI from outside server(diff domain)
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
    }

}
