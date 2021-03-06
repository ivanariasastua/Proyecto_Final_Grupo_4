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
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.services.IEmpleadosAreasTrabajosService;
import org.una.aeropuerto.services.IParametrosSistemaService;

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
    
    @Autowired
    private IParametrosSistemaService paramService;

    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los empleados areas trabajos", response = EmpleadosAreasTrabajosDTO.class, responseContainer = "List", tags = "Empleados_Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(empleadoService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(empleadoService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo empleado area trabajo", response = EmpleadosAreasTrabajosDTO.class, tags = "Empleados_Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody EmpleadosAreasTrabajosDTO empleado) {
        try {
            return new ResponseEntity<>(empleadoService.create(empleado), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody EmpleadosAreasTrabajosDTO empleadoAreaModified) {
        try {
            Optional<EmpleadosAreasTrabajosDTO> empleadoAreaUpdated = empleadoService.update(empleadoAreaModified, id);
            if (empleadoAreaUpdated.isPresent()) {
                return new ResponseEntity<>(empleadoAreaUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/inactivar/{id}/{cedula}/{codigo}")
    @ResponseBody
    @PreAuthorize("hasRole('GERENTE') or hasRole('GESTOR')")
    public ResponseEntity<?> inactivate(@RequestBody EmpleadosAreasTrabajosDTO empleadoAreaInactivar, @PathVariable("id") Long id, @PathVariable("cedula") String cedula, @PathVariable("codigo") String codigo){
        try{
            Optional<ParametrosSistemaDTO> parametro = paramService.findByCodigoIdentificador(cedula);
            if(parametro.isPresent()){
                if(parametro.get().getValor().equals(codigo)){
                    empleadoAreaInactivar.setEstado(false);
                    Optional<EmpleadosAreasTrabajosDTO> empleadoAreaUpdated = empleadoService.update(empleadoAreaInactivar, id);
                    if(empleadoAreaUpdated.isPresent()){
                        return new ResponseEntity<>(empleadoAreaUpdated, HttpStatus.OK);
                    }
                    return new ResponseEntity<>("No se encontro la area de trabajo del empleado a inativar", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>("Los valores del parametro necesario no coinciden", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("No se encontro el parametro de sistema correspondiente", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/area/{term}")
    @ApiOperation(value = "Obtiene una lista de los empleados por el area donde trabaja", response = EmpleadosAreasTrabajosDTO.class, responseContainer = "List", tags = "Empleados_Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> getByArea(@PathVariable(value = "term") String term){
        try{
            Optional<List<EmpleadosAreasTrabajosDTO>> result =empleadoService.findByAreas(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
