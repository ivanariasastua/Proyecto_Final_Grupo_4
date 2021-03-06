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
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.una.aeropuerto.dto.IncidentesCategoriasDTO;
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.services.IIncidentesCategoriasService;
import org.una.aeropuerto.services.IParametrosSistemaService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_categorias")
@Api(tags = {"Incidentes_Categorias"})
public class IncidentesCategoriasController {

    @Autowired
    private IIncidentesCategoriasService incidenteService;

    @Autowired
    private IParametrosSistemaService paramService;
    
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody IncidentesCategoriasDTO incidentesCategorias) {
        try {
            return new ResponseEntity<>(incidenteService.create(incidentesCategorias), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody IncidentesCategoriasDTO modificado, BindingResult bindingResult) {
        try {
            Optional<IncidentesCategoriasDTO> updated = incidenteService.update(modificado, id);
            if (updated.isPresent()) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/inactivar/{id}/{cedula}/{codigo}")
    @ResponseBody
    @PreAuthorize("hasRole('GERENTE') or hasRole('GESTOR')")
    public ResponseEntity<?> inactivate(@RequestBody IncidentesCategoriasDTO categoriaInactivar, @PathVariable("id") Long id, @PathVariable("cedula") String cedula, @PathVariable("codigo") String codigo){
        try{
            Optional<ParametrosSistemaDTO> parametro = paramService.findByCodigoIdentificador(cedula);         
            if(parametro.isPresent()){
                if(parametro.get().getValor().equals(codigo)){
                    categoriaInactivar.setEstado(false);
                    Optional<IncidentesCategoriasDTO> categoriaUpdated = incidenteService.update(categoriaInactivar, id);
                    if(categoriaUpdated.isPresent()){
                        return new ResponseEntity<>(categoriaUpdated, HttpStatus.OK);
                    }
                    return new ResponseEntity<>("No se encontro la categoria a inativar", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>("Los valores del parametro necesario no coinciden", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("No se encontro el parametro de sistema correspondiente", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nombre/{term}")
    @ApiOperation(value = "Obtiene una lista de las categorias por medio de su nombre", response = IncidentesCategoriasDTO.class, responseContainer = "List", tags = "Incidentes_Categorias")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> findByNombre(@PathVariable(value = "term") String term) {
        try {
            Optional<List<IncidentesCategoriasDTO>> result = incidenteService.findByNombre(term);
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
    @ApiOperation(value = "Obtiene una lista de categorias por medio de su estado", response = IncidentesCategoriasDTO.class, responseContainer = "List", tags = "Incidentes_Categorias")
    @PreAuthorize("hasRole('GESTOR') or hasRole('GERENTE')")
    public ResponseEntity<?> findByEstado(@PathVariable(value = "term") boolean term) {
        try {
            return new ResponseEntity<>(incidenteService.findByEstado(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
