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
import org.una.tramites.utils.MapperUtils;

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
            Optional<List<ParametrosSistema>> result = parametrosService.findAll();
            if (result.isPresent()) {
                List<ParametrosSistemaDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ParametrosSistemaDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un parametro del sistema a travez de su identificador unico", response = ParametrosSistemaDTO.class, tags = "Parametros_Sistema")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ParametrosSistema> gastosFound = parametrosService.findById(id);
            if (gastosFound.isPresent()) {
                ParametrosSistemaDTO parametrosDTO = MapperUtils.DtoFromEntity(gastosFound.get(), ParametrosSistemaDTO.class);
                return new ResponseEntity<>(parametrosDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ParametrosSistema parametrosSistema) {
        try {
            ParametrosSistema parametrosCreated = parametrosService.create(parametrosSistema);
            ParametrosSistemaDTO parametrosDto = MapperUtils.DtoFromEntity(parametrosCreated, ParametrosSistemaDTO.class);
            return new ResponseEntity<>(parametrosDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ParametrosSistema traModified) {
        try {
            Optional<ParametrosSistema> traUpdated = parametrosService.update(traModified, id);
            if (traUpdated.isPresent()) {
                ParametrosSistemaDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), ParametrosSistemaDTO.class);
                return new ResponseEntity<>(traDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            parametrosService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteAll() {
        try {
            parametrosService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/valor")
    public ResponseEntity<?> findByValor(@PathVariable(value = "valor") String valor) {
        try {
            Optional<List<ParametrosSistema>> result = parametrosService.findByValor(valor);
            if (result.isPresent()) {
                List<ParametrosSistemaDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), ParametrosSistemaDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
