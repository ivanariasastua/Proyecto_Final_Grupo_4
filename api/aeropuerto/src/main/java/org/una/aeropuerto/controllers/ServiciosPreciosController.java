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
import org.una.aeropuerto.dto.ServiciosPreciosDTO;
import org.una.aeropuerto.services.ServiciosPreciosServiceImplementation;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/servicios_precios")
@Api(tags = {"Servicios_Precios"})
public class ServiciosPreciosController {

    @Autowired
    private ServiciosPreciosServiceImplementation servService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo precio de servicio", response = ServiciosPreciosDTO.class, tags = "Servicios_Precios")
   // @PreAuthorize("hasRole('GESTOR')") 
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ServiciosPreciosDTO servicio) {
        try {
            return new ResponseEntity(servService.create(servicio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/editar/{id}")
    @ResponseBody
    @ApiOperation(value = "Permite modificar un precio del servicio a partir de su Id", response = ServiciosPreciosDTO.class, tags = "Servicios_Precios")
   // @PreAuthorize("hasRole('GESTOR')") 
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ServiciosPreciosDTO servicioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ServiciosPreciosDTO> servicioUpdated = servService.update(servicioDTO, id);
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
}
