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
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.services.IIncidentesRegistradosService;
import org.una.aeropuerto.services.IParametrosSistemaService;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/incidentes_registrados")
@Api(tags = {"Incidentes_Registrados"})
public class IncidentesRegistradosController {

    @Autowired
    private IIncidentesRegistradosService incidenteService;
    
    @Autowired
    private IParametrosSistemaService paramService;

    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verificar el formato y la información de su solicitud con el formato esperado";

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/save")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> create(@RequestBody IncidentesRegistradosDTO incidentesRegistrados) {
        try {
            return new ResponseEntity<>(incidenteService.create(incidentesRegistrados), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/editar/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody IncidentesRegistradosDTO modificado, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<IncidentesRegistradosDTO> updated = incidenteService.update(modificado, id);
                if (updated.isPresent()) {
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } catch (Exception ex) {
                return new ResponseEntity<>(ex.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/area/{term}")
    @ApiOperation(value = "Obtiene una lista de los incidente por medio de su area", response = IncidentesRegistradosDTO.class, responseContainer = "List", tags = "Incidentes_Registrados")
    public ResponseEntity<?> findByArea(@PathVariable(value = "term") String term) {
        try {
            Optional<List<IncidentesRegistradosDTO>> result = incidenteService.findByArea(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/emisor/{term}")
    @ApiOperation(value = "Obtiene una lista de los incidentes por medio de su emisor", response = IncidentesRegistradosDTO.class, responseContainer = "List", tags = "Incidentes_Registrados")
    public ResponseEntity<?> findByEmisor(@PathVariable(value = "term") String term) {
        try {
            Optional<List<IncidentesRegistradosDTO>> result = incidenteService.findByEmisor(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/{term}")
    @ApiOperation(value = "Obtiene una lista de los incidentes por medio de su categoria", response = IncidentesRegistradosDTO.class, responseContainer = "List", tags = "Incidentes_Registrados")
    public ResponseEntity<?> findByCategoria(@PathVariable(value = "term") String term) {
        try {
            Optional<List<IncidentesRegistradosDTO>> result = incidenteService.findByCategoria(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/responsable/{term}")
    @ApiOperation(value = "Obtiene una lista de los incidentes por medio de su responsable", response = IncidentesRegistradosDTO.class, responseContainer = "List", tags = "Incidentes_Registrados")
    public ResponseEntity<?> findByResponsable(@PathVariable(value = "term") String term) {
        try {
            Optional<List<IncidentesRegistradosDTO>> result = incidenteService.findByResponsable(term);
            if (result.isPresent()) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/inactivar/{id}/{cedula}/{codigo}")
    @ResponseBody
    @PreAuthorize("hasRole('GERENTE') or hasRole('GESTOR')")
    public ResponseEntity<?> inactivate(@RequestBody IncidentesRegistradosDTO incidenteInactivar, @PathVariable("id") Long id, @PathVariable("cedula") String cedula, @PathVariable("codigo") String codigo){
        try{
            Optional<ParametrosSistemaDTO> parametro = paramService.findByCodigoIdentificador(cedula);
            if(parametro.isPresent()){
                if(parametro.get().getValor().equals(codigo)){
                    incidenteInactivar.setEstado(false);
                    Optional<IncidentesRegistradosDTO> incidenteUpdated = incidenteService.update(incidenteInactivar, id);
                    if(incidenteUpdated.isPresent()){
                        return new ResponseEntity<>(incidenteUpdated, HttpStatus.OK);
                    }
                    return new ResponseEntity<>("No se encontro el incidente a inativar", HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<>("Los valores del parametro necesario no coinciden", HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("No se encontro el parametro de sistema correspondiente", HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
