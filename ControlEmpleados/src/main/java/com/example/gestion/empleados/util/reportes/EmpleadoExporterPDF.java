/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.gestion.empleados.util.reportes;

import com.example.gestion.empleados.entidades.Empleado;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Vane Proaño
 */
public class EmpleadoExporterPDF {

    public List<Empleado> listaEmpleados;

    public EmpleadoExporterPDF(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
    Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
    fuente.setColor(Color.BLACK);

    PdfPCell celda = new PdfPCell(new Phrase("ID", fuente));
    celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);

    celda = new PdfPCell(new Phrase("Nombre", fuente));
   celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);

    celda = new PdfPCell(new Phrase("Apellido", fuente));
   celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);

    celda = new PdfPCell(new Phrase("Email", fuente));
    celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);

    celda = new PdfPCell(new Phrase("Fecha", fuente));
 celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);

    celda = new PdfPCell(new Phrase("Telefono", fuente));
   celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);

    celda = new PdfPCell(new Phrase("Sexo", fuente));
    celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);

    celda = new PdfPCell(new Phrase("Salario", fuente));
  celda.setBackgroundColor(Color.CYAN);
    celda.setPadding(5);
    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
    tabla.addCell(celda);
}


   private void escribirDatosDeLaTabla(PdfPTable tabla) {
    for (Empleado empleado : listaEmpleados) {
        PdfPCell celda = new PdfPCell(new Phrase(String.valueOf(empleado.getId())));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda = new PdfPCell(new Phrase(empleado.getNombre()));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda = new PdfPCell(new Phrase(empleado.getApellido()));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda = new PdfPCell(new Phrase(empleado.getEmail()));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda = new PdfPCell(new Phrase(empleado.getFecha().toString()));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda = new PdfPCell(new Phrase(String.valueOf(empleado.getTelefono())));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda = new PdfPCell(new Phrase(empleado.getSexo()));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);

        celda = new PdfPCell(new Phrase(String.valueOf(empleado.getSalario())));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        tabla.addCell(celda);
    }
}


public void exportar(HttpServletResponse response) throws IOException{
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());
        
        documento.open();
        
        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLACK);
        fuente.setSize(18);
        
        Paragraph titulo = new Paragraph("Lista de empleados", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);
        
        PdfPTable tabla =new PdfPTable(8); //numero de columnas
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15); // el espacio que habra antes de insertar el pdf
        tabla.setWidths(new float[] {1f, 2.3f, 2.3f, 6f, 2.9f, 3.5f, 2f, 2.2f}); //el espacio de cada columna
        tabla.setWidthPercentage(110);
        
        escribirCabeceraDeLaTabla(tabla);
        escribirDatosDeLaTabla(tabla);
        
        documento.add(tabla);
        documento.close();
    }
}
