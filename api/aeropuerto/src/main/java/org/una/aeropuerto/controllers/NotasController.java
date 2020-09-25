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
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.una.aeropuerto.utils.MapperUtils;

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
    
    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos las notas", response = NotasDTO.class, responseContainer = "List", tags = "Notas")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
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
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Notas> notasFound = notasService.findById(id);
            if (notasFound.isPresent()) {
                NotasDTO gastosDTO = MapperUtils.DtoFromEntity(notasFound.get(), NotasDTO.class);
                return new ResponseEntity<>(gastosDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody Notas notas) {
        try {
            Notas notasCreated = notasService.create(notas);
            NotasDTO notasDto = MapperUtils.DtoFromEntity(notasCreated,NotasDTO.class);
            return new ResponseEntity<>(notasDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
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

    @GetMapping("/notas_gastos_mantenimientos/{id}")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByGServiciosGastosId(@PathVariable(value = "id") Long id) {
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
    
    @PutMapping("/inactivar/{id}")
    @ApiOperation(value = "Inactivar un registro", response = NotasDTO.class, tags = "Empleados_Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> Inactivar(@PathVariable(value = "id") Long id){
        try{
            return new ResponseEntity<>(notasService.inactivate(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
