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
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.entities.ParametrosSistema;
import org.una.aeropuerto.services.IParametrosSistemaService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/parametros_sistema")
@Api(tags = {"Parametros_Sistema"})
public class ParametrosSistemaController {

    @Autowired
    private IParametrosSistemaService parametrosService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los parametros del sistema", response = ParametrosSistemaDTO.class, responseContainer = "List", tags = "Parametros_Sistema")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(parametrosService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un parametro del sistema a travez de su identificador unico", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(parametrosService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo parametro", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ParametrosSistemaDTO parametros) {
        try {
            return new ResponseEntity<>(parametrosService.create(parametros), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un parametro existente", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametrosSistemaDTO servModified) {
        try {
            Optional<ParametrosSistemaDTO> servUpdated = parametrosService.update(servModified, id);
            if (servUpdated.isPresent()) {
                return new ResponseEntity<>(servUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/valor")
    @ApiOperation(value = "Obtiene una lista de los parametros por medio del valor", response = ParametrosSistemaDTO.class, responseContainer = "List", tags = "Parametros_Sistema")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor) {
        try {
            return new ResponseEntity<>(parametrosService.findByValor(valor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
