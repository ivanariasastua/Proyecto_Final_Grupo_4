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
import org.una.aeropuerto.dto.AreasTrabajosDTO;
import org.una.aeropuerto.entities.AreasTrabajos;
import org.una.aeropuerto.services.IAreasTrabajosService;
import org.una.tramites.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/areas_trabajos")
@Api(tags = {"Areas_Trabajos"})
public class AreasTrabajosController {
    @Autowired
    private IAreasTrabajosService areaService;
    
    @GetMapping("/get")
    @ApiOperation(value = "Obtiene una lista de todos las areas de trabajos", response = AreasTrabajosDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            Optional<List<AreasTrabajos>> result = areaService.findAll();
            if (result.isPresent()) {
                List<AreasTrabajosDTO> areaDTO = MapperUtils.DtoListFromEntityList(result.get(),AreasTrabajosDTO.class);
                return new ResponseEntity<>(areaDTO, HttpStatus.OK);
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
            Optional<AreasTrabajos> Found = areaService.findById(id);
            if (Found.isPresent()) {
                AreasTrabajosDTO areaDto = MapperUtils.DtoFromEntity(Found.get(), AreasTrabajosDTO.class);
                return new ResponseEntity<>(areaDto, HttpStatus.OK);
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
    @ApiOperation(value = "Crea una nueva area de trabajo", response = AreasTrabajosDTO.class, tags = "Areas_Trabajos")
    public ResponseEntity<?> create(@RequestBody AreasTrabajosDTO area) {
        try {
            AreasTrabajos areas = MapperUtils.EntityFromDto(area, AreasTrabajos.class);
            areas = areaService.create(areas);
            AreasTrabajosDTO depDto = MapperUtils.DtoFromEntity(areas, AreasTrabajosDTO.class);
            return new ResponseEntity<>(depDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AreasTrabajos depModified) {
        try {
            Optional<AreasTrabajos> depUpdated = areaService.update(depModified, id);
            if (depUpdated.isPresent()) {
                AreasTrabajosDTO depDto = MapperUtils.DtoFromEntity(depUpdated.get(), AreasTrabajosDTO.class);
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
            areaService.delete(id);
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
            areaService.deleteAll();
            if (findAll().getStatusCode() == HttpStatus.NO_CONTENT) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de las areas de trabajo por medio de su nombre", response = AreasTrabajosDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AreasTrabajos>> result = areaService.findByNombre(term);
            if (result.isPresent()) {
                List<AreasTrabajosDTO> depDTO = MapperUtils.DtoListFromEntityList(result.get(),AreasTrabajosDTO.class);
                return new ResponseEntity<>(depDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
