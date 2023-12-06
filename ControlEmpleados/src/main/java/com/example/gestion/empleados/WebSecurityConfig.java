/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gestion.empleados;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 *
 * @author Vane Proaño
 */
@Configuration
@EnableWebSecurity //reconoce que esa clase contendrá configuraciones relacionadas 
//con la seguridad web y se encargará de inicializar y configurar el filtro de seguridad de Spring.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean //permite definir y configurar objetos que serán gestionados por el contenedor de Spring.
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails usuario1 = User
                .withUsername("vane")
                .password("$2a$10$gmlvKgSRpn6choqdYncVPecStkm8kKALgFBTgoW1f7lZK1RHnGDZi")
                .roles("USER")
                .build();

        UserDetails usuario2 = User
                .withUsername("admin")
                .password("$2a$10$gmlvKgSRpn6choqdYncVPecStkm8kKALgFBTgoW1f7lZK1RHnGDZi")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuario1, usuario2);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/form/*", "/eliminar/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout().permitAll();
    }

}
