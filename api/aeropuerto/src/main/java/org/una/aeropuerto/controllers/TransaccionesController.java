/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.aeropuerto.dto.TransaccionesDTO;
import org.una.aeropuerto.services.ITransaccionesService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionesController {

    @Autowired
    private ITransaccionesService transaccionService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea una nueva transaccion", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> create(@RequestBody TransaccionesDTO transaccion) {
        try {
            return new ResponseEntity<>(transaccionService.create(transaccion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accion/{term}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por medio de su accion", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByAccion(@PathVariable(value = "term") String term) {
        try {
            Optional<List<TransaccionesDTO>> result = transaccionService.findByAccion(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fechas/{fechaInicio}/{fechaFinal}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por medio de las fechas", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByFechas(@PathVariable("fechaInicio")Date fechaInicio,@PathVariable("fechaFinal")Date fechaFinal){
        try {
            Optional<List<TransaccionesDTO>> result = transaccionService.findByFechas(fechaInicio,fechaFinal);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/filtro/{fecha1}/{fecha2}/{empleado}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por medio de un flitrado por fechas y/o la cedula de un empleado", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<?> findByFilter(@PathVariable(value = "fecha1") Date fecha1, @PathVariable(value="fecha2")Date fecha2, @PathVariable(value="empleado")String empleado) {
        try {
            return new ResponseEntity<>(transaccionService.filtro(empleado, fecha1, fecha2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
