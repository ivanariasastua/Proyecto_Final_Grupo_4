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
import org.una.aeropuerto.dto.TransaccionesDTO;
import org.una.aeropuerto.entities.Transacciones;
import org.una.aeropuerto.services.ITransaccionesService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/transacciones")
@Api(tags = {"Transacciones"})
public class TransaccionesController {
    
    @Autowired
    private ITransaccionesService transaccionService;
    
    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos las transacciones", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<Transacciones>> result = transaccionService.findAll();
            if (result.isPresent()) {
                List<TransaccionesDTO> transDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionesDTO.class);
                return new ResponseEntity<>(transDTO, HttpStatus.OK);
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
            Optional<Transacciones> Found = transaccionService.findById(id);
            if (Found.isPresent()) {
                TransaccionesDTO tranDto = MapperUtils.DtoFromEntity(Found.get(), TransaccionesDTO.class);
                return new ResponseEntity<>(tranDto, HttpStatus.OK);
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
    @ApiOperation(value = "Crea una nueva transaccion", response = TransaccionesDTO.class, tags = "Transacciones")
    public ResponseEntity<?> create(@RequestBody TransaccionesDTO transaccion) {
        try {
            Transacciones trans = MapperUtils.EntityFromDto(transaccion, Transacciones.class);
            trans = transaccionService.create(trans);
            TransaccionesDTO tranDto = MapperUtils.DtoFromEntity(trans, TransaccionesDTO.class);
            return new ResponseEntity<>(tranDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Transacciones depModified) {
        try {
            Optional<Transacciones> Updated = transaccionService.update(depModified, id);
            if (Updated.isPresent()) {
                TransaccionesDTO depDto = MapperUtils.DtoFromEntity(Updated.get(), TransaccionesDTO.class);
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
            transaccionService.delete(id);
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
            transaccionService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/accion/{term}")
    @ApiOperation(value = "Obtiene una lista de las transacciones por medio de su accion", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByAccion(@PathVariable(value = "term") String term) {
        try {
            Optional<List<Transacciones>> result = transaccionService.findByAccion(term);
            if (result.isPresent()) {
                List<TransaccionesDTO> transDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionesDTO.class);
                return new ResponseEntity<>(transDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @GetMapping("/usuario/{id}")
    @ApiOperation(value = "Obtiene una lista de las transacciones segun el usuario", response = TransaccionesDTO.class, responseContainer = "List", tags = "Transacciones")
    public ResponseEntity<?> findByUsuariosId(@PathVariable(value = "id") Long id) {
        try {
            Optional<List<Transacciones>> result = transaccionService.findByUsuariosId(id);
            if (result.isPresent()) {
                List<TransaccionesDTO> transDTO = MapperUtils.DtoListFromEntityList(result.get(), TransaccionesDTO.class);
                return new ResponseEntity<>(transDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
