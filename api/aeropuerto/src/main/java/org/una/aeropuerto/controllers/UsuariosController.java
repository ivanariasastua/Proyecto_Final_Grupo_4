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
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.entities.Usuarios;
import org.una.aeropuerto.services.IUsuariosService;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/usuarios")
@Api(tags = {"Usuarios"})
public class UsuariosController {
    @Autowired
    private IUsuariosService usuarioService;
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los Usuarios", response = UsuariosDTO.class, responseContainer = "List", tags = "Usuarios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(usuarioService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un usuario a travez de su identificador unico", response = UsuariosDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(usuarioService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("saveUser/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo usuario", response = UsuariosDTO.class, tags = "Usuarios")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody UsuariosDTO usuario) {
        try {
            return new ResponseEntity<>(usuarioService.create(usuario), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un usuario existente", response = UsuariosDTO.class, tags = "Usuarios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody UsuariosDTO usuarioModified) {
        try {
            Optional<UsuariosDTO> usuarioUpdated = usuarioService.update(usuarioModified, id);
            if (usuarioUpdated.isPresent()) {
                return new ResponseEntity<>(usuarioUpdated.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
