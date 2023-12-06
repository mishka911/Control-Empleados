/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gestion.empleados.repositorio;

import com.example.gestion.empleados.entidades.Empleado;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Vane Proaño
 */
public interface EmpleadoRepositorio extends PagingAndSortingRepository<Empleado, Long>{
    
}
