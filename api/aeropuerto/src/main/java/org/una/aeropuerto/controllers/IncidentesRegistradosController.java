 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.services.IIncidentesRegistradosService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_registrados")
@Api(tags = {"Incidentes_Registrados"})
public class IncidentesRegistradosController {

    @Autowired
    private IIncidentesRegistradosService incidenteService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";


    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Incidentes registrados ", response = IncidentesRegistradosDTO.class, responseContainer = "List", tags = "Incidentes_Registrados")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(incidenteService.findAll(),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un incidente registrado a travez de su identificador unico ", response = IncidentesRegistradosDTO.class, tags = "Incidentes_Registrados")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
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
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody IncidentesRegistradosDTO incidentesRegistrados) {
        try {
            return new ResponseEntity<>(incidenteService.create(incidentesRegistrados),HttpStatus.CREATED);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody IncidentesRegistradosDTO modificado, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            try{
                Optional<IncidentesRegistradosDTO> updated = incidenteService.update(modificado, id);
                if(updated.isPresent()){
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }catch(Exception ex){
                return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("categoria/{id}")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
    public ResponseEntity<?> findByCategoriaId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(incidenteService.findByCategoriaId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("areatrabajo/{id}")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
    public ResponseEntity<?> findByAreaTrabajoId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(incidenteService.findByAreaTrabajoId(id),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("filtro/{nomEmisor/cedEmisor/nomResponsable/cedResponsable/nomCategoria/nomArea}")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
    public ResponseEntity filtro(@PathVariable(value = "nomEmisor") String nomEmisor, @PathVariable(value = "cedEmisor") String cedEmisor,
                                 @PathVariable(value = "nomResponsable") String nomResponsable, @PathVariable(value = "cedResponsable") String cedResponsable,
                                 @PathVariable(value = "nomCategoria") String nomCategoria, @PathVariable(value = "nomArea") String nomArea){
        try{
            return new ResponseEntity<>(incidenteService.filtro(nomEmisor, cedEmisor, nomResponsable, cedResponsable, nomCategoria, nomArea),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/inactivar/{id}")
    @ApiOperation(value = "Inactivar un registro", response = IncidentesRegistradosDTO.class, tags = "Incidentes_Registrados")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> Inactivar(@PathVariable(value = "id") Long id){
        try{
            return new ResponseEntity<>(incidenteService.inactive(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
