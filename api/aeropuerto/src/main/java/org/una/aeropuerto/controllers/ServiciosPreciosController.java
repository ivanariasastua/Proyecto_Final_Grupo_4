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
import org.springframework.validation.BindingResult;
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
import org.una.aeropuerto.dto.ServiciosPreciosDTO;
import org.una.aeropuerto.entities.ServiciosPrecios;
import org.una.aeropuerto.services.ServiciosPreciosServiceImplementation;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author cordo
 */
@RestController
@RequestMapping("/servicios_precios")
@Api(tags = {"Servicios_Precios"})
public class ServiciosPreciosController {

    @Autowired
    private ServiciosPreciosServiceImplementation servService;
    
    final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";

    @GetMapping()
    @ApiOperation(value = "Obtiene una lista de todos los servicios precios", response = ServiciosPreciosDTO.class, responseContainer = "List", tags = "Servicios_Precios")
    public @ResponseBody
    ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity(servService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un servicio precios a travez de su identificador unico", response = ServiciosPreciosDTO.class, tags = "Servicios_Precios")
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return new ResponseEntity<>(servService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("save/{value}")
    @ResponseBody
    @ApiOperation(value = "Crea un nuevo precio de servicio", response = ServiciosPreciosDTO.class, tags = "Servicios_Precios")
    public ResponseEntity<?> create(@PathVariable(value = "value") String value, @RequestBody ServiciosPreciosDTO servicio) {
        try {
            return new ResponseEntity(servService.create(servicio), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Permite modificar un precio del servicio a partir de su Id", response = ServiciosPreciosDTO.class, tags = "Servicios_Precios")
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody ServiciosPreciosDTO servicioDTO, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            try {
                Optional<ServiciosPreciosDTO> servicioUpdated = servService.update(servicioDTO, id);
                if (servicioUpdated.isPresent()) {
                    return new ResponseEntity(servicioUpdated, HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(MENSAJE_VERIFICAR_INFORMACION, HttpStatus.BAD_REQUEST);
        }
    }

    //mejorar consulta
//    @GetMapping("/servicios_precios_servicios/{id}")
//    public ResponseEntity<?> findByServiciosId(@PathVariable(value = "id") Long id) {
//        try {
//            Optional<List<ServiciosPrecios>> result = servService.findByServiciosId(id);
//            if (result.isPresent()) {
//                List<ServiciosPreciosDTO> servDto = MapperUtils.DtoListFromEntityList(result.get(), ServiciosPreciosDTO.class);
//                return new ResponseEntity<>(servDto, HttpStatus.OK);
//            }
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception ex) {
//            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
