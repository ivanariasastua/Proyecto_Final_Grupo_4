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
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.repositories.IServiciosGastosRepository;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@Service
public class ReportesServiceImplementation implements IReportesService{
    
    @Autowired
    private IServiciosGastosRepository serviciosGastosRepository;

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesAntesDe(String empresa, Date fecha, String servicio, boolean estadoPago, boolean estadoGasto, String responsable) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroAntesServicioEmpresa(fecha, empresa, servicio, estadoPago, estadoGasto, responsable), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesDespuesDe(String empresa, Date fecha, String servicio, boolean estadoPago, boolean estadoGasto, String responsable) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroDespuesServicioEmpresa(fecha, empresa, servicio, estadoPago, estadoGasto, responsable), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesAntesDe(String empresa, Date fecha, String servicio, String responsable) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroAntesServicioEmpresa(fecha, empresa, servicio, responsable), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> serviciosGastosIncidentesDespuesDe(String empresa, Date fecha, String servicio, String responsable) {
        return ServiceConvertionHelper.findList(serviciosGastosRepository.findByFechaRegistroDespuesServicioEmpresa(fecha, empresa, servicio, responsable), ServiciosGastosDTO.class);
    }
    

}
