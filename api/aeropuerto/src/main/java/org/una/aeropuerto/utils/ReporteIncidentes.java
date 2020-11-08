/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;

/**
 *
 * @author cordo
 */
public class ReporteIncidentes {
    
    private Long id;
    private String fechaRegistro;
    private String emisor;
    private String responsable;
    private String estado;
    private String categoria;
    private String areaTrabajo;
    
    public ReporteIncidentes(IncidentesRegistradosDTO incidente){
        this.id=incidente.getId();
        this.categoria=incidente.getCategoria().getNombre();
        this.estado= incidente.estado ? "Activo":"Inactivo";
        this.emisor=incidente.getEmisor().getNombre();
        this.responsable=incidente.getResponsable().getNombre();
        this.areaTrabajo=incidente.getAreaTrabajo().getNombre();
        this.fechaRegistro = asLocalDate(incidente.getFechaRegistro()).toString();
    }
    
    public Long getId() {
        return id;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public String getEmisor() {
        return emisor;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getEstado() {
        return estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    private static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
}
