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
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.services.IReportesService;
import org.una.aeropuerto.utils.ReportBuilder;

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
    
    @GetMapping("reporteGastos1/{fecha}/{fecha2}/{empresa}/{servicio}/{estPago}/{estGasto}/{responsable}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha")Date fecha, @PathVariable("fecha2")Date fecha2, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("estPago") boolean estPago, @PathVariable("estGasto")boolean estGasto, @PathVariable("responsable")String responsable){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa, fecha, fecha2, servicio, estPago, estGasto, responsable);
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
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa, fecha, fecha2, servicio, responsable);
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
    
    @GetMapping("reporteGastos1/{fecha}/{fecha2}/{empresa}/{servicio}/{estPago}/{responsable}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha")Date fecha, @PathVariable("fecha2")Date fecha2, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("estPago") boolean estPago, @PathVariable("responsable")String responsable){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa, fecha, fecha2, servicio, estPago, responsable);
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
    
    @GetMapping("reporteGastos1/{fecha}/{fecha2}/{empresa}/{servicio}/{estGasto}/{responsable}")
    public ResponseEntity<?> reporteGastosConEstados(@PathVariable("fecha")Date fecha, @PathVariable("fecha2")Date fecha2, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("responsable")String responsable, @PathVariable("estGasto")boolean estGasto){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastos(empresa, fecha, fecha2, servicio, responsable, estGasto);
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
    
    private String convertirReporte(List<ServiciosGastosDTO> lista){
        ObjectOutputStream bytes = null;
        try {
            JasperPrint jprint = ReportBuilder.reporteGastos(lista);
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
}
