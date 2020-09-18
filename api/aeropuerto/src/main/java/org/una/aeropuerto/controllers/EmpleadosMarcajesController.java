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
import org.una.aeropuerto.dto.EmpleadosMarcajesDTO;
import org.una.aeropuerto.entities.EmpleadosMarcajes;
import org.una.aeropuerto.services.IEmpleadosMarcajesService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/empleados_marcajes")
@Api(tags = {"Empleados_Marcajes"})
public class EmpleadosMarcajesController {
    @Autowired
    private IEmpleadosMarcajesService empleadoService;
    
    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los Empleados Marcajes", response = EmpleadosMarcajesDTO.class, responseContainer = "List", tags = "Empleados_Marcajes")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<EmpleadosMarcajes>> result = empleadoService.findAll();
            if (result.isPresent()) {
                List<EmpleadosMarcajesDTO> empDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosMarcajesDTO.class);
                return new ResponseEntity<>(empDTO, HttpStatus.OK);
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
            Optional<EmpleadosMarcajes> empFound = empleadoService.findById(id);
            if (empFound.isPresent()) {
                EmpleadosMarcajesDTO depDto = MapperUtils.DtoFromEntity(empFound.get(), EmpleadosMarcajesDTO.class);
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
    @ApiOperation(value = "Crea un nuevo departamento", response = EmpleadosMarcajesDTO.class, tags = "Empleados_Marcajes")
    public ResponseEntity<?> create(@RequestBody EmpleadosMarcajesDTO empleado) {
        try {
            EmpleadosMarcajes emp = MapperUtils.EntityFromDto(empleado, EmpleadosMarcajes.class);
            emp = empleadoService.create(emp);
            EmpleadosMarcajesDTO depDto = MapperUtils.DtoFromEntity(emp, EmpleadosMarcajesDTO.class);
            return new ResponseEntity<>(depDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody EmpleadosMarcajes depModified) {
        try {
            Optional<EmpleadosMarcajes> depUpdated = empleadoService.update(depModified, id);
            if (depUpdated.isPresent()) {
                EmpleadosMarcajesDTO depDto = MapperUtils.DtoFromEntity(depUpdated.get(), EmpleadosMarcajesDTO.class);
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
    

    
}
