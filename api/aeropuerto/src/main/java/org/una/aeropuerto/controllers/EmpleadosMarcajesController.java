/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.una.aeropuerto.services.IEmpleadosMarcajesService;

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

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nueva marcaje", response = EmpleadosMarcajesDTO.class, tags = "Empleados_Marcajes")
    public ResponseEntity<?> create(@RequestBody EmpleadosMarcajesDTO empleado) {
        try {
            return new ResponseEntity<>(empleadoService.create(empleado), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody EmpleadosMarcajesDTO depModified) {
        try {
            Optional<EmpleadosMarcajesDTO> depUpdated = empleadoService.update(depModified, id);
            if (depUpdated.isPresent()) {
                return new ResponseEntity<>(depUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
    @GetMapping("Ultimo/{id}")
    public ResponseEntity<?> findLastByHorarioId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(empleadoService.findLastByHorarioId(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("reporteHoras/{cedula}/{fecha1}/{fecha2}")
    public ResponseEntity<?> reporteHorasLaboradas(@PathVariable("cedula") String cedula, @PathVariable("fecha1") Date fecha1, 
                                                   @PathVariable("fecha2") Date fecha2){
        try{
            return new ResponseEntity<>(empleadoService.findByEmpleadoCedulaAndFechas(cedula.equals("null") ? "%" : cedula, fecha1, fecha2), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
