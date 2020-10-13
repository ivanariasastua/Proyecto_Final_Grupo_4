/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.entities.Empleados;
import org.una.aeropuerto.services.IEmpleadosService;
import org.una.aeropuerto.utils.Mailer;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@RestController
@RequestMapping("/changePassword")
@Api(tags = {"Password"})
public class CambioContrasenaController {
    
    @Autowired 
    private IEmpleadosService empService;
    
    @GetMapping("/sendEmail/{cedula}")
    @ApiOperation(value = "Funcion que permite solicitar una nueva contraseña temporat", tags="Password")
    public ResponseEntity<?> enviarCorreo(@PathVariable("cedula")String cedula){
        try{
            Optional<EmpleadosDTO> empleado = empService.findByCedulaDTO(cedula);
            if(empleado.isPresent()){
                empService.update(empleado.get(), empleado.get().getId());
                InetAddress ip = InetAddress.getLocalHost();
                Mailer.sendCorreoSolicitud("http://"+ip.getHostAddress()+":8989/changePassword/temporalPassword/"+empleado.get().getCedula(), empleado.get().getCorreo());
                return new ResponseEntity<>("Envio del correo en proceso", HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontro el empleado con esa cedula", HttpStatus.NOT_FOUND);
        }catch(UnknownHostException ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("temporalPassword/{cedula}")
    public ResponseEntity<?> cambiarContrsena(@PathVariable("cedula") String cedula){
        try{
            Optional<Empleados> emp = empService.findByCedula(cedula);
            if(emp.isPresent()){
                EmpleadosDTO dto = MapperUtils.DtoFromEntity(emp.get(), EmpleadosDTO.class);
                if(dto.getSolicitud()){
                    String temp = generateTemporalPassword();
                    dto.setPasswordTemporal(Boolean.TRUE);
                    dto.setSolicitud(Boolean.FALSE);
                    dto.setContrasenaEncriptada(temp);
                    System.out.println(dto.getId());
                    empService.update(dto, dto.getId());
                    Mailer.sendCorreoRespuesta(dto.getNombre(), "Contraseña Temporal: "+temp, "Aquí esta su nueva contraseña: ", dto.getCorreo());
                    return new ResponseEntity<>(Mailer.getRespuesta(emp.get().getNombre(), "Recuerde no compartir con nadie su contraseña", "Pronto recibira un correo con la nueva contraseña"), HttpStatus.OK);
                }else{
                    return new ResponseEntity<>("El usuario no presento ninguna solicitud", HttpStatus.BAD_REQUEST);
                }
            }else{
                return new ResponseEntity<>("No existe el empleado", HttpStatus.NOT_FOUND);
            }
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("cambiarContrasena")
    public ResponseEntity<?> chagePassword(@RequestBody EmpleadosDTO empleado){
        try{
            if(empleado.getPasswordTemporal())  
                return new ResponseEntity<>(empService.update(empleado, empleado.getId()), HttpStatus.OK);
            return new ResponseEntity<>("El usuario no ha solicitado un cambio de contraseña", HttpStatus.BAD_REQUEST);
        }catch(Exception ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    private String generateTemporalPassword(){
        String tempPassword = "";
        for(int i = 0; i < 25; i++){
            tempPassword += (char) (Math.floor(Math.random()*93) + 33);
        }
        return tempPassword;
    }
}
