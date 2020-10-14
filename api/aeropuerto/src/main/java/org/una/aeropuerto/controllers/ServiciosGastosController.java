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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.services.IServiciosGastosService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/gastos_mantenimientos")
@Api(tags = {"Gastos_Mantenimientos"})
public class ServiciosGastosController {

    @Autowired
    private IServiciosGastosService gastosService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo gasto de servicio", response = ServiciosGastosDTO.class, tags = "Servicios_Gastos")
//    @PreAuthorize("hasRole('GESTOR')") 
    public ResponseEntity<?> create(@RequestBody ServiciosGastosDTO servicio) {
        try {
            return new ResponseEntity<>(gastosService.create(servicio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un gasto de servicio existente", response = ServiciosGastosDTO.class, tags = "Servicios_Gastos")
//    @PreAuthorize("hasRole('GESTOR')") 
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServiciosGastosDTO servModified) {
        try {
            Optional<ServiciosGastosDTO> servUpdated = gastosService.update(servModified, id);
            if (servUpdated.isPresent()) {
                return new ResponseEntity<>(servUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/servicio/{term}")
    @ApiOperation(value = "Obtiene una lista de los gastos de servicio por medio de su servicio", response = ServiciosGastosDTO.class, responseContainer = "List", tags = "Servicios_Gastos")
    public ResponseEntity<?> findByServicio(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ServiciosGastosDTO>> result = gastosService.findByServicios(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/empresa/{term}")
    @ApiOperation(value = "Obtiene una lista de los gastos de servicios por medio de su empresa", response = ServiciosGastosDTO.class, responseContainer = "List", tags = "Servicios_Gastos")
    public ResponseEntity<?> findByEmpresa(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ServiciosGastosDTO>> result = gastosService.findByEmpresa(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/numeroContrato/{term}")
    @ApiOperation(value = "Obtiene una lista de los gastos de servicios por medio de su contrato", response = ServiciosGastosDTO.class, responseContainer = "List", tags = "Servicios_Gastos")
    public ResponseEntity<?> findByContrato(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ServiciosGastosDTO>> result = gastosService.findByContrato(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
