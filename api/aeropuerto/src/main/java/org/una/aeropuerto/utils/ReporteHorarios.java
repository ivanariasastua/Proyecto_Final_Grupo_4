/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.una.aeropuerto.dto.EmpleadosMarcajesDTO;

public class ReporteHorarios {
    
    private String cedula;
    private String nombre;
    private String horasLaboradas;
    private String horaEntrada;
    private String horaSalida;
    private String diaEntrada;
    private String diaSalida;
    private final SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    
    List<ReporteHorarios> reportes = new ArrayList();
    
    public ReporteHorarios(){
        
    }
    
    public ReporteHorarios(EmpleadosMarcajesDTO marcaje, String horas){
        this.cedula = marcaje.getEmpleadoHorario().getEmpleado().getCedula();
        this.nombre = marcaje.getEmpleadoHorario().getEmpleado().getNombre();
        this.horaEntrada = formato.format(marcaje.getEmpleadoHorario().getHoraEntrada());
        this.horaSalida = formato.format(marcaje.getEmpleadoHorario().getHoraSalida());
        this.diaEntrada = marcaje.getEmpleadoHorario().getDiaEntrada();
        this.diaSalida = marcaje.getEmpleadoHorario().getDiaSalida();
        this.horasLaboradas = horas;
    }
    
    public List<ReporteHorarios> unirMarcajesMismoHorario(List<EmpleadosMarcajesDTO> lista){
        lista.sort((EmpleadosMarcajesDTO obj1, EmpleadosMarcajesDTO obj2) -> 
        obj2.getEmpleadoHorario().getId().compareTo(obj1.getEmpleadoHorario().getId()));
        
        List<EmpleadosMarcajesDTO> aux = new ArrayList();
        Long id = lista.get(0).getEmpleadoHorario().getId();

        for(EmpleadosMarcajesDTO marcaje : lista){
            if(marcaje.getEmpleadoHorario().getId() == id){
                aux.add(marcaje);
            }else{
                crearReporte(aux);
                id = marcaje.getEmpleadoHorario().getId();
                aux.clear();
                aux.add(marcaje);
            }
        }
        crearReporte(aux);
        return reportes;
    }
    
    public void crearReporte(List<EmpleadosMarcajesDTO> lista){
        int horas = 0;
        for(EmpleadosMarcajesDTO marcaje : lista){
            horas += marcaje.getHorasLaboradas();
        }
        reportes.add(new ReporteHorarios(lista.get(0), String.valueOf(horas)));
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorasLaboradas() {
        return horasLaboradas;
    }

    public String getHoraEntrada() {
        return horaEntrada;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getDiaEntrada() {
        return diaEntrada;
    }

    public String getDiaSalida() {
        return diaSalida;
    }

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public List<ReporteHorarios> getReportes() {
        return reportes;
    }
    
    
}
