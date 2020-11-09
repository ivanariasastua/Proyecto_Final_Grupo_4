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
    
    private Long id;
    private String cedula;
    private String nombre;
    private String horasLaboradas;
    private String horaEntrada;
    private String horaSalida;
    private String diaEntrada;
    private String diaSalida;
    private final SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
    
    public ReporteHorarios(){
        
    }
    
    public ReporteHorarios(EmpleadosMarcajesDTO marcaje, String horas){
        this.id = marcaje.getEmpleadoHorario().getEmpleado().getId();
        this.cedula = marcaje.getEmpleadoHorario().getEmpleado().getCedula();
        this.nombre = marcaje.getEmpleadoHorario().getEmpleado().getNombre();
        this.horaEntrada = formato.format(marcaje.getEmpleadoHorario().getHoraEntrada());
        this.horaSalida = formato.format(marcaje.getEmpleadoHorario().getHoraSalida());
        this.diaEntrada = marcaje.getEmpleadoHorario().getDiaEntrada();
        this.diaSalida = marcaje.getEmpleadoHorario().getDiaSalida();
        this.horasLaboradas = horas;
    }
    
    public static void ordenarListaPorHorarios(List<EmpleadosMarcajesDTO> lista){
        lista.sort((EmpleadosMarcajesDTO obj1, EmpleadosMarcajesDTO obj2) -> 
        obj2.getEmpleadoHorario().getId().compareTo(obj1.getEmpleadoHorario().getId()));
    }
    
    public static List<ReporteHorarios> unirMarcajesSegunHorario(List<EmpleadosMarcajesDTO> lista){
        ordenarListaPorHorarios(lista);
        List<ReporteHorarios> marquejesHorario = new ArrayList();
        Long id = lista.get(0).getEmpleadoHorario().getId();
        int horLaboradas = 0;
        int tamLista = lista.size();
        int cont = 0;
        while(cont < tamLista){
            if(lista.get(cont).getEmpleadoHorario().getId().equals(id)){
                horLaboradas += lista.get(cont).getHorasLaboradas();
                cont++;
            }else{
                marquejesHorario.add(new ReporteHorarios(lista.get(cont-1),String.valueOf(horLaboradas)));
                horLaboradas = 0;
                id = lista.get(cont).getEmpleadoHorario().getId();
            }
        }
        marquejesHorario.add(new ReporteHorarios(lista.get(cont-1),String.valueOf(horLaboradas)));
        return marquejesHorario;
    }
    
    public static String sumarHorasLaboradasEmpleados(List<ReporteHorarios> lista){
        String info = "";
        int horLaboradas = 0;
        int tamLista = lista.size();
        int cont = 0;
        Long empleadoId = lista.get(0).getId();
        while(cont < tamLista){
            if(lista.get(cont).getId().equals(empleadoId)){
                horLaboradas += Integer.parseInt(lista.get(cont).getHorasLaboradas());
                cont++;
            }else{
                info += lista.get(cont-1).getCedula()+" "+lista.get(cont-1).getNombre()+" Total: "+String.valueOf(horLaboradas)+"\n";
                horLaboradas = 0;
                empleadoId = lista.get(cont).getId();
            }
        }
        info += lista.get(cont-1).getCedula()+" "+lista.get(cont-1).getNombre()+" Total: "+String.valueOf(horLaboradas)+"\n";
        return info;
    }
    
    public Long getId(){
        return id;
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
}
