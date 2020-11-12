/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.EmpleadosMarcajesDTO;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.services.IEmpleadosMarcajesService;
import org.una.aeropuerto.services.IReportesService;
import org.una.aeropuerto.utils.ReportBuilder;
import org.una.aeropuerto.utils.ReporteHorarios;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@RestController
@RequestMapping("/reportes")
@Api(tags = {"Reportes"})
public class ReportesController {
    
    @Autowired 
    private IReportesService service;
    
    @Autowired
    private IEmpleadosMarcajesService empMarcajeService;
    
    @GetMapping("reporteGastos1/{fecha}/{fecha2}/{empresa}/{servicio}/{estPago}/{estGasto}/{responsable}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha")Date fecha, @PathVariable("fecha2")Date fecha2, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("estPago") boolean estPago, @PathVariable("estGasto")boolean estGasto, @PathVariable("responsable")String responsable){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, estPago, estGasto, responsable.equals("null") ? "%" : responsable);
            if(optional.isPresent()){
                List<ServiciosGastosDTO> lista = optional.get();
                if(lista == null || lista.isEmpty()){
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                }else{
                    return new ResponseEntity<>(convertirReporte(lista), HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("reporteGastos2/{fecha}/{fecha2}/{empresa}/{servicio}/{responsable}")
    public ResponseEntity<?> reporteGastosSinEstados(@PathVariable("fecha")Date fecha, @PathVariable("fecha2")Date fecha2, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("responsable")String responsable){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, responsable.equals("null") ? "%" : responsable);
            if(optional.isPresent()){
                List<ServiciosGastosDTO> lista = optional.get();
                if(lista == null || lista.isEmpty()){
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                }else{
                    return new ResponseEntity<>(convertirReporte(lista), HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception ex){
            System.out.println("reporte: "+ex);
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("reporteGastos3/{fecha}/{fecha2}/{empresa}/{servicio}/{estPago}/{responsable}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha")Date fecha, @PathVariable("fecha2")Date fecha2, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("estPago") boolean estPago, @PathVariable("responsable")String responsable){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, estPago, responsable.equals("null") ? "%" : responsable);
            if(optional.isPresent()){
                List<ServiciosGastosDTO> lista = optional.get();
                if(lista == null || lista.isEmpty()){
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                }else{
                    return new ResponseEntity<>(convertirReporte(lista), HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("reporteGastos4/{fecha}/{fecha2}/{empresa}/{servicio}/{estGasto}/{responsable}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha")Date fecha, @PathVariable("fecha2")Date fecha2, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("responsable")String responsable, @PathVariable("estGasto")boolean estGasto){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa.equals("null") ? "%" : empresa, fecha, fecha2, servicio.equals("null") ? "%" : servicio, responsable.equals("null") ? "%" : responsable, estGasto);
            if(optional.isPresent()){
                List<ServiciosGastosDTO> lista = optional.get();
                if(lista == null || lista.isEmpty()){
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                }else{
                    return new ResponseEntity<>(convertirReporte(lista), HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("reporteHoras/{cedula}/{fecha1}/{fecha2}")
    public ResponseEntity<?> reporteHorasLaboradas(@PathVariable("cedula") String cedula, @PathVariable("fecha1") Date fecha1, 
                                                   @PathVariable("fecha2") Date fecha2){
        try{
            Optional<List<EmpleadosMarcajesDTO>> optional = empMarcajeService.findByEmpleadoCedulaAndFechas(cedula, fecha1, fecha2);
            if(optional.isPresent()){
                List<EmpleadosMarcajesDTO> lista = optional.get();
                if(lista == null || lista.isEmpty()){
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                }else{
                    return new ResponseEntity<>(convertirReporteHorasLaboradas(lista), HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private String convertirReporte(List<ServiciosGastosDTO> lista){
        try {
            JasperPrint jprint = ReportBuilder.reporteGastos(lista);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ObjectOutputStream bytes = new ObjectOutputStream(byteArray);
            bytes.writeObject(jprint);
            bytes.flush();
            return Base64.getEncoder().encodeToString(byteArray.toByteArray());
        } catch (IOException ex) {
            System.out.println("Error generando reporte: ["+ex+"]");
        }
        return "";
    }

    private String convertirReporteHorasLaboradas(List<EmpleadosMarcajesDTO> lista){
        ObjectOutputStream bytes = null;
        try {
            List<ReporteHorarios> reportes = ReporteHorarios.unirMarcajesSegunHorario(lista);
            String info = ReporteHorarios.sumarHorasLaboradasEmpleados(reportes);
            JasperPrint jprint = ReportBuilder.reporteHorasLaboradas(reportes,info);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            bytes = new ObjectOutputStream(byteArray);
            bytes.writeObject(jprint);
            bytes.flush();
            return Base64.getEncoder().encodeToString(byteArray.toByteArray());
        } catch (IOException ex) {
            System.out.println("Error generando reporte: ["+ex+"]");
        } finally {
            try {
                bytes.close();
            } catch (IOException ex) {}
        }
        return "";
    }
    @GetMapping("reporteIncidente2/{fechaIni}/{fechaFin}/{responsable}/{emisor}")
    public ResponseEntity<?> reporteIncidentes2(@PathVariable("fechaIni")Date fechaIni,@PathVariable("fechaFin")Date fechaFin, @PathVariable("responsable")String responsable, @PathVariable("emisor")String emisor){
        Optional<List<IncidentesRegistradosDTO>> optional = service.incidentesRegistradosReportes(fechaIni,fechaFin,responsable.equals("null") ? "%" : responsable, emisor.equals("null") ? "%" : emisor);
        return crearReporteIncident(optional);
    }
    @GetMapping("reporteIncidente/{fechaIni}/{fechaFin}/{estado}/{responsable}/{emisor}")
    public ResponseEntity<?> reporteIncidentes(@PathVariable("fechaIni")Date fechaIni,@PathVariable("fechaFin")Date fechaFin,@PathVariable("estado")boolean estado, @PathVariable("responsable")String responsable, @PathVariable("emisor")String emisor){
        Optional<List<IncidentesRegistradosDTO>> optional = service.incidentesRegistradosReportes(fechaIni,fechaFin, estado, responsable.equals("null") ? "%" : responsable, emisor.equals("null") ? "%" : emisor);
        return crearReporteIncident(optional);
    }
    
    public ResponseEntity crearReporteIncident(Optional<List<IncidentesRegistradosDTO>> optional){
        try{
            if(optional.isPresent()){
                List<IncidentesRegistradosDTO> lista = optional.get();
                if(lista == null || lista.isEmpty()){
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                }else{
                    JasperPrint jprint = ReportBuilder.reporteIncidente(lista);
                    ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                    ObjectOutputStream bytes = new ObjectOutputStream(byteArray);
                    bytes.writeObject(jprint);
                    bytes.flush();
                    String temp = Base64.getEncoder().encodeToString(byteArray.toByteArray());
                    return new ResponseEntity<>(temp, HttpStatus.OK);
                }
            }else{
                return new ResponseEntity<>("Lista Vacia", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
