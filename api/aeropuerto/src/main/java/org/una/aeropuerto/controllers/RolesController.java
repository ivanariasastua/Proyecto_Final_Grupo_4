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
import org.una.tramites.utils.MapperUtils;

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
            Optional<List<Roles>> result = rolService.findAll();
            if (result.isPresent()) {
                List<RolesDTO> departamentosDTO = MapperUtils.DtoListFromEntityList(result.get(), RolesDTO.class);
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
            Optional<Roles> departamentoFound = rolService.findById(id);
            if (departamentoFound.isPresent()) {
                RolesDTO depDto = MapperUtils.DtoFromEntity(departamentoFound.get(),RolesDTO.class);
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
    @ApiOperation(value = "Crea un nuevo rol", response = RolesDTO.class, tags = "Roles")
    public ResponseEntity<?> create(@RequestBody RolesDTO rol) {
        try {
            Roles roles = MapperUtils.EntityFromDto(rol, Roles.class);
            roles = rolService.create(roles);
            RolesDTO depDto = MapperUtils.DtoFromEntity(roles, RolesDTO.class);
            return new ResponseEntity<>(depDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Roles depModified) {
        try {
            Optional<Roles> depUpdated = rolService.update(depModified, id);
            if (depUpdated.isPresent()) {
                RolesDTO depDto = MapperUtils.DtoFromEntity(depUpdated.get(), RolesDTO.class);
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
            rolService.delete(id);
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
            rolService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de roles por medio de su nombre", response = RolesDTO.class, responseContainer = "List", tags = "Roles")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Roles>> result = rolService.findByNombre(term);
            if (result.isPresent()) {
                List<RolesDTO> rolDTO = MapperUtils.DtoListFromEntityList(result.get(), RolesDTO.class);
                return new ResponseEntity<>(rolDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
