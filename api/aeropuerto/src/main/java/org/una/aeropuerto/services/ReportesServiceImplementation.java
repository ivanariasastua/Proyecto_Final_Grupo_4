    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.dto.TransaccionesDTO;
import org.una.aeropuerto.repositories.IIncidentesRegistradosRepository;
import org.una.aeropuerto.repositories.IServiciosGastosRepository;
import org.una.aeropuerto.repositories.ITransaccionesRepository;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@Service
public class ReportesServiceImplementation implements IReportesService{
    
    @Autowired
    private IServiciosGastosRepository serviciosGastosRepository;
    
    @Autowired
    private IIncidentesRegistradosRepository incidentesRepository;
    
    @Autowired
    private ITransaccionesRepository transRepository;

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, boolean estadoPago, boolean estadoGasto, String responsable) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroServicioEmpresaEstados(fecha, fecha2, empresa, servicio, estadoPago, estadoGasto, responsable), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, String responsable) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroServicioEmpresa(fecha, fecha2, empresa, servicio, responsable), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, boolean estadoPago, String responsable) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroServicioEmpresaEstadoPago(fecha, fecha2, empresa, servicio, estadoPago, responsable), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastos(String empresa, Date fecha, Date fecha2, String servicio, String responsable, boolean estadoGasto) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroServicioEmpresaEstadoGasto(fecha, fecha2, empresa, servicio, responsable, estadoGasto), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<IncidentesRegistradosDTO>> incidentesRegistradosReportes(Date fechaIni, Date fechaFin,boolean estado, String responsable, String emisor) {
        return ServiceConvertionHelper.findList(incidentesRepository.findByFiltro(fechaIni,fechaFin, estado, responsable, emisor), IncidentesRegistradosDTO.class);
    }
    
    @Override
    public Optional<List<IncidentesRegistradosDTO>> incidentesRegistradosReportes(Date fechaIni, Date fechaFin, String responsable, String emisor) {
        return ServiceConvertionHelper.findList(incidentesRepository.findByFiltro(fechaIni,fechaFin, responsable, emisor), IncidentesRegistradosDTO.class);
    }

    @Override
    public Optional<List<TransaccionesDTO>> transacciones(Date fecha1, Date fecha2) {
        return ServiceConvertionHelper.findList(transRepository.findByFechaRegistroBetween(fecha1, fecha2), TransaccionesDTO.class);
    }
}