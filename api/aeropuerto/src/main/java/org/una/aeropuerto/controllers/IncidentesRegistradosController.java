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
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.entities.IncidentesRegistrados;
import org.una.aeropuerto.services.IIncidentesRegistradosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_registrados")
@Api(tags = {"Incidentes_Registrados"})
public class IncidentesRegistradosController {

    @Autowired
    private IIncidentesRegistradosService incidenteService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Incidentes registrados ", response = IncidentesRegistradosDTO.class, responseContainer = "List", tags = "Incidentes_Registrados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<IncidentesRegistrados>> result = incidenteService.findAll();
            if (result.isPresent()) {
                List<IncidentesRegistradosDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), IncidentesRegistradosDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un incidente registrado a travez de su identificador unico ", response = IncidentesRegistradosDTO.class, tags = "Incidentes_Registrados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<IncidentesRegistrados> incidenteFound = incidenteService.findById(id);
            if (incidenteFound.isPresent()) {
                IncidentesRegistradosDTO incidenteDTO = MapperUtils.DtoFromEntity(incidenteFound.get(), IncidentesRegistradosDTO.class);
                return new ResponseEntity<>(incidenteDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody IncidentesRegistrados incidentesRegistrados) {
        try {
            IncidentesRegistrados incidenteCreated = incidenteService.create(incidentesRegistrados);
            IncidentesRegistradosDTO incidenteDto = MapperUtils.DtoFromEntity(incidenteCreated, IncidentesRegistradosDTO.class);
            return new ResponseEntity<>(incidenteDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody IncidentesRegistrados traModified) {
        try {
            Optional<IncidentesRegistrados> traUpdated = incidenteService.update(traModified, id);
            if (traUpdated.isPresent()) {
                IncidentesRegistradosDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), IncidentesRegistradosDTO.class);
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
            incidenteService.delete(id);
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
            incidenteService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
