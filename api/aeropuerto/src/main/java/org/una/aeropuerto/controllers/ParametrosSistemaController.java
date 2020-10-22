/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
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
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.services.IParametrosSistemaService;

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

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un parametro del sistema a travez de su identificador unico", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(parametrosService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo parametro", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('GERENTE')")
    public ResponseEntity<?> create(@RequestBody ParametrosSistemaDTO parametros) {
        try {
            return new ResponseEntity<>(parametrosService.create(parametros), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un parametro existente", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    @PreAuthorize("hasRole('ADMINISTRADOR') or hasRole('GERENTE')")
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

    @GetMapping("/valor/{valor}")
    @ApiOperation(value = "Obtiene un parametro por medio del valor", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor) {
        try {
            return new ResponseEntity<>(parametrosService.findByValor(valor), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/codigoIdentiicador/{codigo}")
    @ApiOperation(value = "Obtiene un parametro por medio del codigo", response = ParametrosSistemaDTO.class,  tags = "Parametros_Sistema")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByCodigoIdentificador(@PathVariable(value = "codigo") String codigo) {
        try {
            return new ResponseEntity<>(parametrosService.findByCodigoIdentificador(codigo), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fechaRegisto/{fecha1}/{fecha2}")
    @ApiOperation(value = "Obtiene una lista de los parametros por medio de un intervalo de fechas", response = ParametrosSistemaDTO.class, responseContainer = "List", tags = "Parametros_Sistema")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByFechaRegistro(@PathVariable(value = "fecha1") String fecha1, @PathVariable("fecha2") String fecha2) {
        try {
            return new ResponseEntity<>(parametrosService.findByFechaRegistro(fecha1, fecha2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/fechaModificacion/{fecha1}/fecha2")
    @ApiOperation(value = "Obtiene una lista de los parametros por medio de un intervalo de fechas", response = ParametrosSistemaDTO.class, responseContainer = "List", tags = "Parametros_Sistema")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByFechaModificacion(@PathVariable(value = "fecha1") Date fecha1, @PathVariable("fecha2") Date fecha2) {
        try {
            return new ResponseEntity<>(parametrosService.findByFechaModificacion(fecha1, fecha2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
