package com.example.hotelmng.mysecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/tohome","/employeepanel","/guests")
                .hasAuthority("Employee")
                .and()

                .authorizeRequests()
                .antMatchers("/tohome","/managerpanel","/guests","/employeepanel","/sectors","/rooms","/sectortype","/roomtype")
                .hasAuthority("Manager")
                .and()

                .authorizeRequests()
                .antMatchers("/tohome","/adminpanel")
                .hasAuthority("Admin")
                .and()

                .authorizeRequests()
                .antMatchers("/tohome")
                .hasAnyAuthority("Manager","Employee","Admin")
                .and()


                .authorizeRequests()
                .antMatchers( "/login","/")
                .permitAll()

                .anyRequest()
                .authenticated()
                .and()

                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()

                .logout()
                .permitAll()
                .and()

                .httpBasic()
                .and()
                .csrf().disable();
    }

}