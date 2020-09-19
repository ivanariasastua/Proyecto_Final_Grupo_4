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
import org.una.aeropuerto.dto.IncidentesEstadosDTO;
import org.una.aeropuerto.entities.IncidentesEstados;
import org.una.aeropuerto.services.IIncidentesEstadosService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_estados")
@Api(tags = {"Incidentes_Estados"})
public class IncidentesEstadosController {

    @Autowired
    private IIncidentesEstadosService incidenteService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Incidentes estados ", response = IncidentesEstadosDTO.class, responseContainer = "List", tags = "Incidentes_Estados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<IncidentesEstados>> result = incidenteService.findAll();
            if (result.isPresent()) {
                List<IncidentesEstadosDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), IncidentesEstadosDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un incidente estado a travez de su identificador unico ", response = IncidentesEstadosDTO.class, tags = "Incidentes_Estados")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<IncidentesEstados> incidenteFound = incidenteService.findById(id);
            if (incidenteFound.isPresent()) {
                IncidentesEstadosDTO incidenteDTO = MapperUtils.DtoFromEntity(incidenteFound.get(), IncidentesEstadosDTO.class);
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
    public ResponseEntity<?> create(@RequestBody IncidentesEstados incidentesEstados) {
        try {
            IncidentesEstados incidenteCreated = incidenteService.create(incidentesEstados);
            IncidentesEstadosDTO incidenteDto = MapperUtils.DtoFromEntity(incidenteCreated, IncidentesEstadosDTO.class);
            return new ResponseEntity<>(incidenteDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody IncidentesEstados traModified) {
        try {
            Optional<IncidentesEstados> traUpdated = incidenteService.update(traModified, id);
            if (traUpdated.isPresent()) {
                IncidentesEstadosDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), IncidentesEstadosDTO.class);
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

    @GetMapping("/estado")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "estado") String estado) {
        try {
            Optional<List<IncidentesEstados>> result = incidenteService.findByEstado(estado);
            if (result.isPresent()) {
                List<IncidentesEstadosDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), IncidentesEstadosDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
