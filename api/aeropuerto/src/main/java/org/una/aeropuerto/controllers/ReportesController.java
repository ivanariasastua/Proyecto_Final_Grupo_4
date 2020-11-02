/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    
    @GetMapping("reporteGastos1/{fecha}/{empresa}/{servicio}/{estPago}/{estGasto}/{responsable}")
    public ResponseEntity<?> reporteGastosFechaAntesDe(@PathVariable("fecha")Date fecha, @PathVariable("empresa")String empresa, 
    @PathVariable("servicio")String servicio, @PathVariable("estPago") boolean estPago, @PathVariable("estGasto")boolean estGasto, @PathVariable("responsable")String responsable){
        try{
            Optional<List<ServiciosGastosDTO>> optional = service.serviciosGastosIncidentesAntesDe(empresa, fecha, servicio, estPago, estGasto, responsable);
            if(optional.isPresent()){
                List<ServiciosGastosDTO> lista = optional.get();
                if(lista == null || lista.isEmpty()){
                    return new ResponseEntity<>("La lista está vacía", HttpStatus.NOT_FOUND);
                }else{
                    JasperPrint jprint = ReportBuilder.reporteGastos(lista);
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
            System.out.println("reporte: "+ex);
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
