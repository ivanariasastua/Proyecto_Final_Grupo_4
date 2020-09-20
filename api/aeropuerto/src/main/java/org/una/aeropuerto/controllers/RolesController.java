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
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.entities.Roles;
import org.una.aeropuerto.services.IRolesService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/roles")
@Api(tags = {"Roles"})
public class RolesController {

    @Autowired
    private IRolesService rolService;

    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos los roles", response = RolesDTO.class, responseContainer = "List", tags = "Roles")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(rolService.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(rolService.findById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo rol", response = RolesDTO.class, tags = "Roles")
    public ResponseEntity<?> create(@RequestBody RolesDTO rol) {
        try {
            return new ResponseEntity<>(rolService.create(rol), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody RolesDTO depModified) {
        try {
            Optional<RolesDTO> depUpdated = rolService.update(depModified, id);
            if (depUpdated.isPresent()) {
                return new ResponseEntity<>(depUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de roles por medio de su nombre", response = RolesDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            return new ResponseEntity<>(rolService.findByNombre(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
