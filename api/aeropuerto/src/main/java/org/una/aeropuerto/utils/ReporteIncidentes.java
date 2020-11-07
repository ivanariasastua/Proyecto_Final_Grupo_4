/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.text.SimpleDateFormat;
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
    private final SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");

    
    public ReporteIncidentes(IncidentesRegistradosDTO incidente){
        this.id=incidente.getId();
        this.categoria=incidente.getCategoria().getNombre();
        this.estado= incidente.estado ? "Activo":"Inactivo";
        this.emisor=incidente.getEmisor().getNombre();
        this.responsable=incidente.getResponsable().getNombre();
        this.areaTrabajo=incidente.getAreaTrabajo().getNombre();
        this.fechaRegistro = formato.format(incidente.getFechaRegistro());
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

    public SimpleDateFormat getFormato() {
        return formato;
    }
    
    
}
