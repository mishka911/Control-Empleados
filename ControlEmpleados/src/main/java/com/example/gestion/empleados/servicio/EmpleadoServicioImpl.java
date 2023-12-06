/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gestion.empleados.servicio;

import com.example.gestion.empleados.entidades.Empleado;
import com.example.gestion.empleados.repositorio.EmpleadoRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Vane Proaño
 */

@Service
public class EmpleadoServicioImpl implements EmpleadoServicio{

    @Autowired
    private EmpleadoRepositorio empleadoRepositorio;
    
    @Override
    @Transactional(readOnly = true) //solo sera de lectura y no se podra editar los datos
    public List<Empleado> findAll() {
       return (List<Empleado>) empleadoRepositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Empleado> findAll(Pageable pageable) {
        return  empleadoRepositorio.findAll(pageable);
    }

    @Override
    @Transactional //va a permitir la edicion de datos
    public void save(Empleado empleado) {
        empleadoRepositorio.save(empleado);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findOne(Long id) {
        return empleadoRepositorio.findById(id).orElse(null); // si lo encuentra por id sino nada
    }

    @Override
    @Transactional
    public void delete(Long id) {
        empleadoRepositorio.deleteById(id);
    }
    
}
