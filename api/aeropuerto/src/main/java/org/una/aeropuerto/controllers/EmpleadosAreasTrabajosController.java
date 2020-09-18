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
import org.una.aeropuerto.dto.EmpleadosAreasTrabajosDTO;
import org.una.aeropuerto.entities.EmpleadosAreasTrabajos;
import org.una.aeropuerto.services.IEmpleadosAreasTrabajosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/empleados_areas_trabajos")
@Api(tags = {"Empleados_Areas_Trabajos"})
public class EmpleadosAreasTrabajosController {

    @Autowired
    private IEmpleadosAreasTrabajosService empleadoService;

    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los empleados areas trabajos", response = EmpleadosAreasTrabajosDTO.class, responseContainer = "List", tags = "Empleados_Areas_Trabajos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<EmpleadosAreasTrabajos>> result = empleadoService.findAll();
            if (result.isPresent()) {
                List<EmpleadosAreasTrabajosDTO> empleadoDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosAreasTrabajosDTO.class);
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
            Optional<EmpleadosAreasTrabajos> areaFound = empleadoService.findById(id);
            if (areaFound.isPresent()) {
                EmpleadosAreasTrabajosDTO areaDto = MapperUtils.DtoFromEntity(areaFound.get(), EmpleadosAreasTrabajosDTO.class);
                return new ResponseEntity<>(areaDto, HttpStatus.OK);
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
    @ApiOperation(value = "Crea un nuevo empleado area trabajo", response = EmpleadosAreasTrabajosDTO.class, tags = "Empleados_Areas_Trabajos")
    public ResponseEntity<?> create(@RequestBody EmpleadosAreasTrabajosDTO empleado) {
        try {
            EmpleadosAreasTrabajos area = MapperUtils.EntityFromDto(empleado, EmpleadosAreasTrabajos.class);
            area = empleadoService.create(area);
            EmpleadosAreasTrabajosDTO depDto = MapperUtils.DtoFromEntity(area, EmpleadosAreasTrabajosDTO.class);
            return new ResponseEntity<>(depDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody EmpleadosAreasTrabajos depModified) {
        try {
            Optional<EmpleadosAreasTrabajos> depUpdated = empleadoService.update(depModified, id);
            if (depUpdated.isPresent()) {
                EmpleadosAreasTrabajosDTO depDto = MapperUtils.DtoFromEntity(depUpdated.get(), EmpleadosAreasTrabajosDTO.class);
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
    @ApiOperation(value = "Obtiene una lista de empleados areas trabajos segun el empleado", response = EmpleadosAreasTrabajosDTO.class, responseContainer = "List", tags = "Empleados_Areas_Trabajos")
    public ResponseEntity<?> findByEmpleadosId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<EmpleadosAreasTrabajos>> result = empleadoService.findByEmpleadoId(id);
            if (result.isPresent()) {
                List<EmpleadosAreasTrabajosDTO> areaDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosAreasTrabajosDTO.class);
                return new ResponseEntity<>(areaDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/area/{id}")
    @ApiOperation(value = "Obtiene una lista de empleados areas trabajos segun el area de trabajo", response = EmpleadosAreasTrabajosDTO.class, responseContainer = "List", tags = "Empleados_Areas_Trabajos")
    public ResponseEntity<?> findByAreasTrabajosId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<EmpleadosAreasTrabajos>> result = empleadoService.findByAreaTrabajoId(id);
            if (result.isPresent()) {
                List<EmpleadosAreasTrabajosDTO> areaDTO = MapperUtils.DtoListFromEntityList(result.get(), EmpleadosAreasTrabajosDTO.class);
                return new ResponseEntity<>(areaDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
