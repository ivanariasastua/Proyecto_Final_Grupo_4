/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.services.IEmpleadosService;

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
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
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
    @ApiOperation(value = "Crea un nuevo empleado", response = EmpleadosDTO.class, tags = "Empleados")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody EmpleadosDTO empleado) {
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
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody EmpleadosDTO depModified) {
        try {
            Optional<EmpleadosDTO> depUpdated = empleadoService.update(depModified, id);
            if (depUpdated.isPresent()) {
                return new ResponseEntity<>(depUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
    
    @GetMapping("cedula/{cedula}")
    @ApiOperation(value = "Obtiene una lista de los empleados por cedula", response = EmpleadosDTO.class, responseContainer = "List", tags = "Empleados")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> getByCedula(@PathVariable("cedula") String cedula){
        try{
            return new ResponseEntity<>(empleadoService.findByCedulaAproximate(cedula), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("cedulaUnica/{cedula}")
    @ApiOperation(value = "Obtiene un empleado  por cedula", response = EmpleadosDTO.class, tags = "Empleados")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> getByCedulaUnica(@PathVariable("cedula") String cedula){
        try{
            return new ResponseEntity<>(empleadoService.findByCedulaDTO(cedula), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("nombre/{nombre}")
    @ApiOperation(value = "Obtiene una lista de los empleados por nombre", response = EmpleadosDTO.class, responseContainer = "List", tags = "Empleados")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> getByNombre(@PathVariable("nombre") String nombre){
        try{
            return new ResponseEntity<>(empleadoService.findByNombreAproximate(nombre), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("area/{area}")
    @ApiOperation(value = "Obtiene una lista de los empleados por el area donde trabaja", response = EmpleadosDTO.class, responseContainer = "List", tags = "Empleados")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> getByArea(@PathVariable("area") String area){
        try{
            return new ResponseEntity<>(empleadoService.findByAreas(area), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/getNoAprobados")
    @ApiOperation(value = "Obtiene una lista de los empleados cuyo rol aun no se ha aprobado", response = EmpleadosDTO.class, responseContainer = "List", tags = "Empleados")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> getNoAprobados(){
        try{
            return new ResponseEntity<>(empleadoService.findNoAprobados(), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
}
