/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.IncidentesRegistradosEstadosDTO;
import org.una.aeropuerto.services.IIncidentesRegistradosEstadosService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_registrados_estados")
@Api(tags = {"Incidentes_Registrados_Estados"})
public class IncidentesRegistradosEstadosController {

    @Autowired
    private IIncidentesRegistradosEstadosService incidenteService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody IncidentesRegistradosEstadosDTO incidentesRegistradosEstados) {
        try {
            return new ResponseEntity<>(incidenteService.create(incidentesRegistradosEstados),HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
