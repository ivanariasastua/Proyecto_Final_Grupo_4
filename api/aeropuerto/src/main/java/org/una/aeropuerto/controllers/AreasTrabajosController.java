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
import org.una.aeropuerto.dto.AreasTrabajosDTO;
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.services.IAreasTrabajosService;
import org.una.aeropuerto.services.IParametrosSistemaService;

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
    
    @Autowired
    private IParametrosSistemaService paramService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @ApiOperation(value = "Crea una nueva area de trabajo", response = AreasTrabajosDTO.class, tags = "Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody AreasTrabajosDTO area) {
        try {
            return new ResponseEntity<>(areaService.create(area), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/editar/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody AreasTrabajosDTO areaModified) {
        try {
            if(!areaModified.isEstado())
                return new ResponseEntity<>("No puede modificar inactivar este registro desde aquí", HttpStatus.NOT_ACCEPTABLE);
            Optional<AreasTrabajosDTO> areaUpdated = areaService.update(areaModified, id);
            if (areaUpdated.isPresent()) {
                return new ResponseEntity<>(areaUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/inactivar/{id}/{cedula}/{codigo}")
    @ResponseBody
    @PreAuthorize("hasRole('GERENTE') or hasRole('GESTOR')")
    public ResponseEntity<?> inactivate(@RequestBody AreasTrabajosDTO areaInactivar, @PathVariable("id") Long id, @PathVariable("cedula") String cedula, @PathVariable("codigo") String codigo){
        try{
            Optional<ParametrosSistemaDTO> parametro = paramService.findByCodigoIdentificador(cedula);
            if(parametro.isPresent()){
                if(parametro.get().getValor().equals(codigo)){
                    areaInactivar.setEstado(false);
                    Optional<AreasTrabajosDTO> areaUpdated = areaService.update(areaInactivar, id);
                    if(areaUpdated.isPresent()){
                        return new ResponseEntity<>(areaUpdated, HttpStatus.OK);
                    }
                    return new ResponseEntity<>("No se encontro el area a inativar", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>("Los valores del parametro necesario no coinciden", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("No se encontro el parametro de sistema correspondiente", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de las areas de trabajo por medio de su nombre", response = AreasTrabajosDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<AreasTrabajosDTO>> result = areaService.findByNombre(term);
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
    @ApiOperation(value = "Obtiene una lista de las areas de trabajo por medio de su estado", response = AreasTrabajosDTO.class, responseContainer = "List", tags = "Areas_Trabajos")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(areaService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
