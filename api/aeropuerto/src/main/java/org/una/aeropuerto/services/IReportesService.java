/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.EmpleadosHorariosDTO;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.dto.TransaccionesDTO;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public interface IReportesService {
    
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesAntesDe(String empresa, Date fecha, String servicio, boolean estadoPago, boolean estadoGasto, String responsable);
    
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesDespuesDe(String empresa, Date fecha, String servicio, boolean estadoPago, boolean estadoGasto, String responsable);
    
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesAntesDe(String empresa, Date fecha, String servicio, String responsable);
    
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesDespuesDe(String empresa, Date fecha, String servicio, String responsable);
    /*
    public Optional<List<IncidentesRegistradosDTO>> incidentesRegistradosReportes();

    public Optional<List<TransaccionesDTO>> transaccionesReportes();
    
    public Optional<List<EmpleadosHorariosDTO>> horariosReportes();*/
}
