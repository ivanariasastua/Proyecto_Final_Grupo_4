/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.IncidentesEstadosDTO;
import org.una.aeropuerto.entities.IncidentesEstados;
import org.una.aeropuerto.services.IIncidentesEstadosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_estados")
@Api(tags = {"Incidentes_Estados"})
public class IncidentesEstadosController {

    @Autowired
    private IIncidentesEstadosService incidenteService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Incidentes estados ", response = IncidentesEstadosDTO.class, responseContainer = "List", tags = "Incidentes_Estados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(incidenteService.findAll(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un incidente estado a travez de su identificador unico ", response = IncidentesEstadosDTO.class, tags = "Incidentes_Estados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(incidenteService.findById(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody IncidentesEstadosDTO incidentesEstados) {
        try {
            return new ResponseEntity<>(incidenteService.create(incidentesEstados),HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody IncidentesEstadosDTO modificado, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            try{
                Optional<IncidentesEstadosDTO> updated = incidenteService.update(modificado, id);
                if(updated.isPresent()){
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estado) {
        try {
            return new ResponseEntity<>(incidenteService.findByEstado(estado),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
