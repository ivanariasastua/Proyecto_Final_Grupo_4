/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.dto.TransaccionesDTO;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public interface IReportesService {
    
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, boolean estadoPago, boolean estadoGasto, String responsable);
        
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, boolean estadoPago, String responsable);
    
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, String responsable, boolean estadoGasto);
    
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, String responsable);
    
    public Optional<List<IncidentesRegistradosDTO>> incidentesRegistradosReportes(Date fechaIni,boolean estado, String responsable,String emisor);

    public Optional<List<TransaccionesDTO>> transacciones(Date fecha1, Date fecha2);
}
