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
import org.una.aeropuerto.dto.EmpleadosHorariosDTO;
import org.una.aeropuerto.entities.EmpleadosHorarios;
import org.una.aeropuerto.services.IEmpleadosHorariosService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author cordo
 */

@RestController
@RequestMapping("/empleados_horarios")
@Api(tags = {"Empleados_Horarios"})
public class EmpleadosHorariosController {
    @Autowired
    private IEmpleadosHorariosService empleadoService;
    
    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los empleados horarios", response = EmpleadosHorariosDTO.class, responseContainer = "List", tags = "Empleados_Horarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<EmpleadosHorarios>> result = empleadoService.findAll();
            if (result.isPresent()) {
                List<EmpleadosHorariosDTO> departamentosDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosHorariosDTO.class);
                return new ResponseEntity<>(departamentosDTO, HttpStatus.OK);
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
            Optional<EmpleadosHorarios> departamentoFound = empleadoService.findById(id);
            if (departamentoFound.isPresent()) {
                EmpleadosHorariosDTO depDto = MapperUtils.DtoFromEntity(departamentoFound.get(), EmpleadosHorariosDTO.class);
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
    @ApiOperation(value = "Crea un nuevo departamento", response = EmpleadosHorariosDTO.class, tags = "Empleados_Horarios")
    public ResponseEntity<?> create(@RequestBody EmpleadosHorariosDTO empleado) {
        try {
            EmpleadosHorarios depart = MapperUtils.EntityFromDto(empleado, EmpleadosHorarios.class);
            depart = empleadoService.create(depart);
            EmpleadosHorariosDTO depDto = MapperUtils.DtoFromEntity(depart, EmpleadosHorariosDTO.class);
            return new ResponseEntity<>(depDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody EmpleadosHorarios depModified) {
        try {
            Optional<EmpleadosHorarios> depUpdated = empleadoService.update(depModified, id);
            if (depUpdated.isPresent()) {
                EmpleadosHorariosDTO depDto = MapperUtils.DtoFromEntity(depUpdated.get(), EmpleadosHorariosDTO.class);
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
    
        @GetMapping("/empleado/{id}")
    @ApiOperation(value = "Obtiene una lista de empleados horarios segun el empleado", response = EmpleadosHorariosDTO.class, responseContainer = "List", tags = "Empleados_Horarios")
    public ResponseEntity<?> findByEmpleadosId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<EmpleadosHorarios>> result = empleadoService.findByEmpleadosId(id);
            if (result.isPresent()) {
                List<EmpleadosHorariosDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosHorariosDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
