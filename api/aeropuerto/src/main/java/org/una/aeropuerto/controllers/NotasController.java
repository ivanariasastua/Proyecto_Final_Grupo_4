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
import org.una.aeropuerto.dto.NotasDTO;
import org.una.aeropuerto.entities.Notas;
import org.una.aeropuerto.services.INotasService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/notas")
@Api(tags = {"Notas"})
public class NotasController {
    
    @Autowired
    private INotasService notasService;
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las notas", response = NotasDTO.class, responseContainer = "List", tags = "Notas")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Notas>> result = notasService.findAll();
            if (result.isPresent()) {
                List<NotasDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), NotasDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene una nota a travez de su identificador unico", response = NotasDTO.class, tags = "Notas")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Notas> gastosFound = notasService.findById(id);
            if (gastosFound.isPresent()) {
                NotasDTO gastosDTO = MapperUtils.DtoFromEntity(gastosFound.get(), NotasDTO.class);
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
    public ResponseEntity<?> create(@RequestBody Notas notas) {
        try {
            Notas gastosCreated = notasService.create(notas);
            NotasDTO gastosDto = MapperUtils.DtoFromEntity(gastosCreated,NotasDTO.class);
            return new ResponseEntity<>(gastosDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Notas traModified) {
        try {
            Optional<Notas> traUpdated = notasService.update(traModified, id);
            if (traUpdated.isPresent()) {
                NotasDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), NotasDTO.class);
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
            notasService.delete(id);
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
            notasService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/notas_gastos_mantenimientos/{id}")
    public ResponseEntity<?> findByGastosMantenimientosId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<Notas>> result = notasService.findByGastosMantenimientosId(id);
            if (result.isPresent()) {
                List<NotasDTO> servDto = MapperUtils.DtoListFromEntityList(result.get(), NotasDTO.class);
                return new ResponseEntity<>(servDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
