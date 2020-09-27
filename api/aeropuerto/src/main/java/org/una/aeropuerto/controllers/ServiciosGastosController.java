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
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.services.IServiciosGastosService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/gastos_mantenimientos")
@Api(tags = {"Gastos_Mantenimientos"})
public class ServiciosGastosController {

    @Autowired
    private IServiciosGastosService gastosService;

    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los Gastos de Mantenimientos", response = ServiciosGastosDTO.class, responseContainer = "List", tags = "Gastos_Mantenimientos")
//    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')") 
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(gastosService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un tipo de gasto a travez de su identificador unico", response = ServiciosGastosDTO.class, tags = "Gastos_Mantenimientos")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(gastosService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo gasto de servicio", response = ServiciosGastosDTO.class, tags = "Servicios_Gastos")
//    @PreAuthorize("hasRole('GESTOR')") 
    public ResponseEntity<?> create(@RequestBody ServiciosGastosDTO servicio) {
        try {
            return new ResponseEntity<>(gastosService.create(servicio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un gasto de servicio existente", response = ServiciosGastosDTO.class, tags = "Servicios_Gastos")
//    @PreAuthorize("hasRole('GESTOR')") 
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody ServiciosGastosDTO servModified) {
        try {
            Optional<ServiciosGastosDTO> servUpdated = gastosService.update(servModified, id);
            if (servUpdated.isPresent()) {
                return new ResponseEntity<>(servUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/gastos_servicios/{id}")
    @ApiOperation(value = "Obtiene una lista de los gastos de servicios por medio de su servicio", response = ServiciosGastosDTO.class, responseContainer = "List", tags = "Servicios_Gastos")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByServiciosId(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(gastosService.findByServiciosId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/inactivar/{id}")
    @ApiOperation(value = "Inactivar un gasto de servicio", response = ServiciosGastosDTO.class, tags = "Empleados_Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> Inactivar(@PathVariable(value = "id") Long id){
        try{
            return new ResponseEntity<>(gastosService.inactivate(id), HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
