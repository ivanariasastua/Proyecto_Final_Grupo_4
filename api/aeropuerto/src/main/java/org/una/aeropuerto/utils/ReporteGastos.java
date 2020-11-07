/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import org.una.aeropuerto.dto.ServiciosGastosDTO;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public class ReporteGastos {
    private Long id;
    private String servicio;
    private String costo;
    private String empresa;
    private String fechaRegistro;
    private String estadoPago;
    private String estadoGasto;
    private String numeroContrato;
    private String perioricidad;
    private String duracion;
    private String responsable;
    private String estado;
    private final SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    
    public ReporteGastos(ServiciosGastosDTO gasto){
        this.id = gasto.getId();
        this.servicio = gasto.getServicio().getNombre();
        this.costo = gasto.getServicio().getServiciosPrecios().isEmpty()? "0.0" : String.valueOf(gasto.getServicio().getServiciosPrecios().stream().max(Comparator.comparing(p -> p.getId())).get().getCosto());
        this.empresa = gasto.getEmpresa();
        this.fechaRegistro = formato.format(gasto.getFechaRegistro());
        this.estadoPago = gasto.isEstadoPago() ? "P" : "NP";
        this.estadoGasto = gasto.isEstadoGasto() ? "A" : "I";
        this.numeroContrato = gasto.getNumeroContrato();
        Integer periodo = gasto.getPerioricidad();
        if(periodo == 1){
            this.perioricidad = "Diario";
        }else if(periodo == 2){
            this.perioricidad = "Semanal";
        }else if(periodo == 3){
            this.perioricidad = "Quincenal";
        }else if(periodo == 4){
            this.perioricidad = "Mensual";
        }else{
            this.perioricidad = "Anual";
        }
        this.duracion = gasto.getDuracion().toString();
        this.responsable = gasto.getResponsable().getCedula();
        this.estado = gasto.isEstado()? "A" : "I";
    }
    
    public Long getId() {
        return id;
    }

    public String getServicio() {
        return servicio;
    }

    public String getCosto() {
        return costo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public String getEstadoGasto() {
        return estadoGasto;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public String getPerioricidad() {
        return perioricidad;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getEstado() {
        return estado;
    }
    
    
}
