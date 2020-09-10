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
import org.una.aeropuerto.dto.ServiciosPreciosDTO;
import org.una.aeropuerto.entities.ServiciosPrecios;
import org.una.aeropuerto.services.ServiciosPreciosServiceImplementation;
import org.una.tramites.utils.MapperUtils;

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

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los servicios precios", response = ServiciosPreciosDTO.class, responseContainer = "List", tags = "Servicios_Precios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ServiciosPrecios>> result = servService.findAll();
            if (result.isPresent()) {
                List<ServiciosPreciosDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), ServiciosPreciosDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un servicio precios a travez de su identificador unico", response = ServiciosPreciosDTO.class, tags = "Servicios_Precios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ServiciosPrecios> servFound = servService.findById(id);
            if (servFound.isPresent()) {
                ServiciosPreciosDTO servDto = MapperUtils.DtoFromEntity(servFound.get(), ServiciosPreciosDTO.class);
                return new ResponseEntity<>(servDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ServiciosPrecios serviciosPrecios) {
        try {
            ServiciosPrecios servCreated = servService.create(serviciosPrecios);
            ServiciosPreciosDTO servDto = MapperUtils.DtoFromEntity(servCreated, ServiciosPreciosDTO.class);
            return new ResponseEntity<>(servDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServiciosPrecios varModified) {
        try {
            Optional<ServiciosPrecios> servUpdated = servService.update(varModified, id);
            if (servUpdated.isPresent()) {
                ServiciosPreciosDTO usuarioDto = MapperUtils.DtoFromEntity(servUpdated.get(), ServiciosPreciosDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            servService.delete(id);
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
            servService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/servicios_precios_servicios/{id}")
    public ResponseEntity<?> findByServiciosId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<ServiciosPrecios>> result = servService.findByServiciosId(id);
            if (result.isPresent()) {
                List<ServiciosPreciosDTO> servDto = MapperUtils.DtoListFromEntityList(result.get(), ServiciosPreciosDTO.class);
                return new ResponseEntity<>(servDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
