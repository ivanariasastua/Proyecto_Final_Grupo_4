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
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.entities.Empleados;
import org.una.aeropuerto.services.IEmpleadosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/empleados")
@Api(tags = {"Empleados"})
public class EmpleadosController {
    @Autowired
    private IEmpleadosService empleadoService;
    
    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos las transacciones", response = EmpleadosDTO.class, responseContainer = "List", tags = "Empleados")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Empleados>> result = empleadoService.findAll();
            if (result.isPresent()) {
                List<EmpleadosDTO> empleadoDTO = MapperUtils.DtoListFromEntityList(result.get(),EmpleadosDTO.class);
                return new ResponseEntity<>(empleadoDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            Optional<Empleados> empleadoFound = empleadoService.findById(id);
            if (empleadoFound.isPresent()) {
                EmpleadosDTO depDto = MapperUtils.DtoFromEntity(empleadoFound.get(), EmpleadosDTO.class);
                return new ResponseEntity<>(depDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo departamento", response = EmpleadosDTO.class, tags = "Empleados")
    public ResponseEntity<?> create(@RequestBody EmpleadosDTO empleado) {
        try {
            Empleados emp = MapperUtils.EntityFromDto(empleado, Empleados.class);
            emp = empleadoService.create(emp);
            EmpleadosDTO depDto = MapperUtils.DtoFromEntity(emp, EmpleadosDTO.class);
            return new ResponseEntity<>(depDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Empleados depModified) {
        try {
            Optional<Empleados> depUpdated = empleadoService.update(depModified, id);
            if (depUpdated.isPresent()) {
                EmpleadosDTO depDto = MapperUtils.DtoFromEntity(depUpdated.get(), EmpleadosDTO.class);
                return new ResponseEntity<>(depDto, HttpStatus.OK);
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
            empleadoService.delete(id);
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
            empleadoService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de departamentos por medio de su nombre", response = EmpleadosDTO.class, responseContainer = "List", tags = "Empleados")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Empleados>> result = empleadoService.findByNombre(term);
            if (result.isPresent()) {
                List<EmpleadosDTO> depDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosDTO.class);
                return new ResponseEntity<>(depDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/cedula/{term}")
    @ApiOperation(value = "Obtiene una lista de departamentos por medio de su nombre", response = EmpleadosDTO.class, responseContainer = "List", tags = "Empleados")
    public ResponseEntity<?> findByCedula(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Empleados>> result = empleadoService.findByCedula(term);
            if (result.isPresent()) {
                List<EmpleadosDTO> depDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosDTO.class);
                return new ResponseEntity<>(depDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
