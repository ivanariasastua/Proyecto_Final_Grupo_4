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
import org.una.aeropuerto.dto.IncidentesCategoriasDTO;
import org.una.aeropuerto.entities.IncidentesCategorias;
import org.una.aeropuerto.services.IIncidentesCategoriasService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_categorias")
@Api(tags = {"Incidentes_Categorias"})
public class IncidentesCategoriasController {

    @Autowired
    private IIncidentesCategoriasService incidenteService;

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Incidentes de Categorias", response = IncidentesCategoriasDTO.class, responseContainer = "List", tags = "Incidentes_Categorias")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<IncidentesCategorias>> result = incidenteService.findAll();
            if (result.isPresent()) {
                List<IncidentesCategoriasDTO> resultDto = MapperUtils.DtoListFromEntityList(result.get(), IncidentesCategoriasDTO.class);
                return new ResponseEntity<>(resultDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un incidente de categoria a travez de su identificador unico", response = IncidentesCategoriasDTO.class, tags = "Incidentes_Categorias")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<IncidentesCategorias> incidenteFound = incidenteService.findById(id);
            if (incidenteFound.isPresent()) {
                IncidentesCategoriasDTO incidenteDTO = MapperUtils.DtoFromEntity(incidenteFound.get(), IncidentesCategoriasDTO.class);
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
    public ResponseEntity<?> create(@RequestBody IncidentesCategorias incidentesCategorias) {
        try {
            IncidentesCategorias incidenteCreated = incidenteService.create(incidentesCategorias);
            IncidentesCategoriasDTO incidenteDto = MapperUtils.DtoFromEntity(incidenteCreated, IncidentesCategoriasDTO.class);
            return new ResponseEntity<>(incidenteDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody IncidentesCategorias traModified) {
        try {
            Optional<IncidentesCategorias> traUpdated = incidenteService.update(traModified, id);
            if (traUpdated.isPresent()) {
                IncidentesCategoriasDTO traDto = MapperUtils.DtoFromEntity(traUpdated.get(), IncidentesCategoriasDTO.class);
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

    @GetMapping("/nombre")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre") String nombre) {
        try {
            Optional<List<IncidentesCategorias>> result = incidenteService.findByNombre(nombre);
            if (result.isPresent()) {
                List<IncidentesCategoriasDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), IncidentesCategoriasDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
