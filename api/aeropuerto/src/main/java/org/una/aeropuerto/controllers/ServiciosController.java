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
import org.una.aeropuerto.dto.ServiciosDTO;
import org.una.aeropuerto.services.ServiciosServiceImplementation;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/servicios")
@Api(tags = {"Servicios"})
public class ServiciosController {
    @Autowired
    private ServiciosServiceImplementation serviciosService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las Servicios", response = ServiciosDTO.class, responseContainer = "List", tags = "Servicios")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
    public @ResponseBody ResponseEntity<?> findAll(){
        try {
            return new ResponseEntity(serviciosService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un servicio a travez de su identificador unico", response = ServiciosDTO.class, tags = "Servicios")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(serviciosService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo servicio", response = ServiciosDTO.class, tags = "Servicios")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ServiciosDTO servicio) {
        try {
            return new ResponseEntity(serviciosService.create(servicio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Permite modificar un Servicio a partir de su Id", response = ServiciosDTO.class, tags = "Servicios")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ServiciosDTO servicioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ServiciosDTO> servicioUpdated = serviciosService.update(servicioDTO, id);
                if (servicioUpdated.isPresent()) {
                    return new ResponseEntity(servicioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de servicios por medio de su nombre", response = ServiciosDTO.class, responseContainer = "List", tags = "Servicios")
    @PreAuthorize("hasAnyRole('GESTOR','GERENTE','ADMINISTRADOR')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(serviciosService.findByNombre(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/inactivar/{id}")
    @ApiOperation(value = "Inactivar un registro", response = ServiciosDTO.class, tags = "Empleados_Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> Inactivar(@PathVariable(value = "id") Long id){
        try{
            return new ResponseEntity<>(serviciosService.inactivate(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
