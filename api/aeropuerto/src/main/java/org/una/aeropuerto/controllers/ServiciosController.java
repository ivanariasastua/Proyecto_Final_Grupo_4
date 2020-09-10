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
import org.una.aeropuerto.dto.ServiciosDTO;
import org.una.aeropuerto.entities.Servicios;
import org.una.aeropuerto.services.ServiciosServiceImplementation;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/servicios")
@Api(tags = {"Servicios"})
public class ServiciosController {
    @Autowired
    private ServiciosServiceImplementation serviciosService;
    
    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos las Servicios", response = ServiciosDTO.class, responseContainer = "List", tags = "Servicios")
    public @ResponseBody ResponseEntity<?> findAll(){
        try{
            Optional<List<Servicios>> result = serviciosService.findAll();
            if(result.isPresent()){
                List<ServiciosDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), ServiciosDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un servicio a travez de su identificador unico", response = ServiciosDTO.class, tags = "Servicios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {

            Optional<Servicios> serviciosFound = serviciosService.findById(id);
            if (serviciosFound.isPresent()) {
                ServiciosDTO serviciosDto = MapperUtils.DtoFromEntity(serviciosFound.get(), ServiciosDTO.class);
                return new ResponseEntity<>(serviciosDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Servicios servicios) {
        try {
            Servicios servCreated = serviciosService.create(servicios);
            ServiciosDTO servDto = MapperUtils.DtoFromEntity(servCreated, ServiciosDTO.class);
            return new ResponseEntity<>(servDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Servicios servModified) {
        try {
            Optional<Servicios> servUpdated = serviciosService.update(servModified, id);
            if (servUpdated.isPresent()) {
                ServiciosDTO servDto = MapperUtils.DtoFromEntity(servUpdated.get(), ServiciosDTO.class);
                return new ResponseEntity<>(servDto, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            serviciosService.delete(id);
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
            serviciosService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "nombre")String nombre){
        try{
            Optional<List<Servicios>> result = serviciosService.findByNombre(nombre);
            if(result.isPresent()){
                List<ServiciosDTO> resultDTO = MapperUtils.DtoListFromEntityList(result.get(), ServiciosDTO.class);
                return new ResponseEntity<>(resultDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
