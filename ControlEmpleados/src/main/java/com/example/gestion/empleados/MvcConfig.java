/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gestion.empleados;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author Vane Proaño
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    
    //para registrar controladores de vista (ViewControllers) predeterminados, sin la necesidad de escribir un controlador completo.
    //addViewControllers() específicamente se utiliza para mapear rutas a controladores de vista predefinidos sin 
    //la necesidad de implementar un controlador completo, puedes utilizar addViewControllers() 
    //para asignar una URL a una vista específica sin tener que definir un controlador separado.
    
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
       registry.addViewController("/login").setViewName("login");
    }
    
}
