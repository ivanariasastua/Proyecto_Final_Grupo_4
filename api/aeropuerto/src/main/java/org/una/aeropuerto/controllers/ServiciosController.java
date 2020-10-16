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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.ServiciosDTO;
import org.una.aeropuerto.services.IServiciosService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/servicios")
@Api(tags = {"Servicios"})
public class ServiciosController {

    @Autowired
    private IServiciosService serviciosService;

    @GetMapping("/get/{id}")
    //@PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(serviciosService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo servicio", response = ServiciosDTO.class, tags = "Servicios")
    //  @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody ServiciosDTO servicio) {
        try {
            return new ResponseEntity(serviciosService.create(servicio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @ApiOperation(value = "Permite modificar un Servicio a partir de su Id", response = ServiciosDTO.class, tags = "Servicios")
    //   @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ServiciosDTO servicioDTO) {
        try {
            Optional<ServiciosDTO> servicioUpdated = serviciosService.update(servicioDTO, id);
            if (servicioUpdated.isPresent()) {
                return new ResponseEntity(servicioUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de servicios por medio de su nombre", response = ServiciosDTO.class, responseContainer = "List", tags = "Servicios")
  //  @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<ServiciosDTO>> result = serviciosService.findByNombre(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/estado/{term}")
    @ApiOperation(value = "Obtiene una lista de los servicios por medio de su estado", response = ServiciosDTO.class, responseContainer = "List", tags = "Servicios")
    //  @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE') or hasRole('ADMINISTRADOR')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(serviciosService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
