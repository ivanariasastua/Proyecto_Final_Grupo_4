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
            Optional<List<Usuarios>> result = usuarioService.findAll();
            if (result.isPresent()) {
                List<UsuariosDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuariosDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un usuario a travez de su identificador unico", response = UsuariosDTO.class, tags = "Usuarios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Usuarios> usuarioFound = usuarioService.findById(id);
            if (usuarioFound.isPresent()) {
                UsuariosDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioFound.get(), UsuariosDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
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
            Usuarios user = MapperUtils.EntityFromDto(usuario, Usuarios.class);
    //        user.setPasswordEncriptado(value);
            user = usuarioService.create(user);
            UsuariosDTO usuarioDto = MapperUtils.DtoFromEntity(user, UsuariosDTO.class);
            return new ResponseEntity<>(usuarioDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Modifica un usuario existente", response = UsuariosDTO.class, tags = "Usuarios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Usuarios usuarioModified) {
        try {
            Optional<Usuarios> usuarioUpdated = usuarioService.update(usuarioModified, id);
            if (usuarioUpdated.isPresent()) {
                UsuariosDTO usuarioDto = MapperUtils.DtoFromEntity(usuarioUpdated.get(), UsuariosDTO.class);
                return new ResponseEntity<>(usuarioDto, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Borra un usario por su identificador unico", tags = "Usuarios")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            usuarioService.delete(id);
            if (findById(id).getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Borra todos los usuario", tags = "Usuarios")
    public ResponseEntity<?> deleteAll() {
        try {
            usuarioService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/rol/{id}")
    @ApiOperation(value = "Obtiene una lista de usuarios segun el rol donde se desempeña", response = UsuariosDTO.class, responseContainer = "List", tags = "Usuarios")
    public ResponseEntity<?> findByRolesId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<Usuarios>> result = usuarioService.findUsersByRolesId(id);
            if (result.isPresent()) {
                List<UsuariosDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(result.get(), UsuariosDTO.class);
                return new ResponseEntity<>(usuariosDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
