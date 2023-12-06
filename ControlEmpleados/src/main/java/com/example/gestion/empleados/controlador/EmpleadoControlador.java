/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gestion.empleados.controlador;

import com.example.gestion.empleados.entidades.Empleado;
import com.example.gestion.empleados.servicio.EmpleadoServicio;
import com.example.gestion.empleados.util.paginacion.PageRender;
import com.example.gestion.empleados.util.reportes.EmpleadoExporterExcel;
import com.example.gestion.empleados.util.reportes.EmpleadoExporterPDF;
import java.io.IOException;
import java.net.FileNameMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Vane Proaño
 */
@Controller
public class EmpleadoControlador {

    @Autowired
    private EmpleadoServicio empleadoServicio;

    @GetMapping("/ver/{id}")
    public String verDetallesDelEmpleado(@PathVariable(value = "id") Long id, Map<String, Object> modelo, RedirectAttributes flash) {
        Empleado empleado = empleadoServicio.findOne(id);
        if (empleado == null) {
            flash.addFlashAttribute("error", "El empleado no se encuentra en la base de datos");
            return "redirect:/listar";
        }
        modelo.put("empleado", empleado);
        modelo.put("titulo", "Detalles del empleado " + empleado.getNombre());
        return "ver";
    }

    @GetMapping({"/", "/listar", ""})
    public String listarEmpleado(@RequestParam(name = "page", defaultValue = "0") int page, Model modelo) {
        Pageable pageRequest = PageRequest.of(page, 5); // esto si mostrara 5 elementos por pagina
        Page<Empleado> empleados = empleadoServicio.findAll(pageRequest);
        PageRender<Empleado> pageRender = new PageRender<>("/listar", empleados);
        modelo.addAttribute("titulo", "Listado de empleados");
        modelo.addAttribute("empleados", empleados);
        modelo.addAttribute("page", pageRender);

        return "listar";
    }

    @GetMapping("/form")
    public String mostrarFormularioDeRegistrarEmpleado(Map<String, Object> modelo) {
        Empleado empleado = new Empleado();
        modelo.put("empleado", empleado);
        modelo.put("titulo", "Registro Empleados");
        return "form";
    }

    @PostMapping("/form")
    public String guardarEmpleado(@Valid Empleado empleado, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status) {//valid por lo que estamos usando validaciones y bindresult te trae todos los atributos con la validacion
        if (result.hasErrors()) {// si tiene errror
            modelo.addAttribute("titulo", "Registro de empleado");
            return "form";
        }
//si no lo tiene 
        String mensaje = (empleado.getId() != null) ? "El empleado ha sido editado con exito" : "Empleado registrado con exito"; //el segundo mensaje es cuando no hay el id y lo estamos creando
        empleadoServicio.save(empleado);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/listar";
    }

    @GetMapping("/form/{id}")
    public String editarEmpleado(@PathVariable(value = "id") Long id, Map<String, Object> modelo, RedirectAttributes flash) {
        Empleado empleado = null;
        if (id > 0) {
            empleado = empleadoServicio.findOne(id);
            if (empleado == null) {
                flash.addFlashAttribute("error", "El ID del empleado no ha sido encontrado");
                return "redirect:/listar";
            }
        } else {
            flash.addFlashAttribute("error", "El ID no puede ser cero");
            return "redirect:/listar";
        }
        modelo.put("empleado", empleado);
        modelo.put("titulo", "Edicion de empleado");
        return "form";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (id > 0) {
            empleadoServicio.delete(id);
            flash.addFlashAttribute("success", "Empleado eliminado con exito");

        }
        return "redirect:/listar";
    }
    
    @GetMapping("/exportarPDF")
    public void exportarListadoDeEmpeladosEnPDF (HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf"); //contendio que vas a descargar osea pdf
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd_HH:mm:ss"); // hh: es para las horas que son 23 en le dia, mm (meses del a;o), ss es paa los segundos (0 al 59)
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Empleados_" + fechaActual + ".pdf";
       
        response.setHeader(cabecera,valor);
        
        List<Empleado> empleados = empleadoServicio.findAll();
        
        EmpleadoExporterPDF exporter = new EmpleadoExporterPDF(empleados);
        exporter.exportar(response);
        
        
    }
    
       @GetMapping("/exportarExcel")
    public void exportarListadoDeEmpeladosEnExcel (HttpServletResponse response) throws IOException{
        response.setContentType("application/octet-stream"); //responda en formato excel
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd_HH:mm:ss"); // hh: es para las horas que son 23 en le dia, mm (meses del a;o), ss es paa los segundos (0 al 59)
        String fechaActual = dateFormatter.format(new Date());
        
        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Empleados_" + fechaActual + ".xlsx";
       
        response.setHeader(cabecera,valor);
        
        List<Empleado> empleados = empleadoServicio.findAll();
        
        EmpleadoExporterExcel exporter = new EmpleadoExporterExcel(empleados);
        exporter.exportar(response);
        
        
    }

}
