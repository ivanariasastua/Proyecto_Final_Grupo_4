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
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.entities.ServiciosGastos;
import org.una.aeropuerto.utils.MapperUtils;
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

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Gastos de Mantenimientos", response = ServiciosGastosDTO.class, responseContainer = "List", tags = "Gastos_Mantenimientos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<ServiciosGastos>> result = gastosService.findAll();
            if (result.isPresent()) {
                List<ServiciosGastosDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), ServiciosGastosDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tipo de gasto a travez de su identificador unico", response = ServiciosGastosDTO.class, tags = "Gastos_Mantenimientos")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<ServiciosGastos> gastosFound = gastosService.findById(id);
            if (gastosFound.isPresent()) {
                ServiciosGastosDTO gastosDTO = MapperUtils.DtoFromEntity(gastosFound.get(), ServiciosGastosDTO.class);
                return new ResponseEntity<>(gastosDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ServiciosGastos gastosMantenimientos) {
        try {
            ServiciosGastos gastosCreated = gastosService.create(gastosMantenimientos);
            ServiciosGastosDTO gastosDto = MapperUtils.DtoFromEntity(gastosCreated, ServiciosGastosDTO.class);
            return new ResponseEntity<>(gastosDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServiciosGastos traModified) {
        try {
            Optional<ServiciosGastos> traUpdated = gastosService.update(traModified, id);
            if (traUpdated.isPresent()) {
                ServiciosGastosDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), ServiciosGastosDTO.class);
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
            gastosService.delete(id);
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
            gastosService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gastos_mantenimientos_servicios/{id}")
    public ResponseEntity<?> findByServiciosId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<ServiciosGastos>> result = gastosService.findByServiciosId(id);
            if (result.isPresent()) {
                List<ServiciosGastosDTO> servDto = MapperUtils.DtoListFromEntityList(result.get(), ServiciosGastosDTO.class);
                return new ResponseEntity<>(servDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa")
    public ResponseEntity<?> findByEmpresa(@PathVariable(value = "empresa") String empresa) {
        try {
            Optional<List<ServiciosGastos>> result = gastosService.findByEmpresa(empresa);
            if (result.isPresent()) {
                List<ServiciosGastosDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), ServiciosGastosDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
