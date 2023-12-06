/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.gestion.empleados.servicio;

import com.example.gestion.empleados.entidades.Empleado;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Vane Proaño
 */
public interface EmpleadoServicio {
    
    public List<Empleado> findAll(); //es para enlistar todos los empleados y los encuentre
    public Page<Empleado> findAll(Pageable pageable); // es para la paginacion e igual encuentre a todos
    public void save(Empleado empleado);
    public Empleado findOne(Long id);
    public void delete(Long id);
}
