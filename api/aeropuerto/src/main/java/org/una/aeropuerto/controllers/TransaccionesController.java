/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos las transacciones", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('AUDITOR')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(transaccionService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tipo de gasto a travez de su identificador unico", response = TransaccionesDTO.class, tags = "Transacciones")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(transaccionService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea una nueva transaccion", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody TransaccionesDTO transaccion) {
        try {
            return new ResponseEntity<>(transaccionService.create(transaccion), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica una transaccion existente", response = TransaccionesDTO.class, tags = "Transacciones")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody TransaccionesDTO tranModified) {
        try {
            Optional<TransaccionesDTO> servUpdated = transaccionService.update(tranModified, id);
            if (servUpdated.isPresent()) {
                return new ResponseEntity<>(servUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/accion/{term}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por medio de su accion", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    @PreAuthorize("hasRole('AUDITOR')")
    public ResponseEntity<?> findByAccion(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(transaccionService.findByAccion(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
