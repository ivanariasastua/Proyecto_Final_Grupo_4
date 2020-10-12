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
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.entities.Empleados;
import org.una.aeropuerto.services.IAuthenticationService;
import org.una.aeropuerto.services.IEmpleadosService;
import org.una.aeropuerto.utils.Mailer;
import org.una.aeropuerto.utils.MapperUtils;

/**
 *
 * @author Ivan Josué Arias Astua
 */
@RestController
@RequestMapping("/authentication")
@Api(tags = {"Seguridad"})
public class AuthenticationController {
    
    @Autowired
    private IAuthenticationService service;
    
    @Autowired 
    private IEmpleadosService empService;
    
    final String MENSAJE_VERIFICAR_CREDENCIALES = "Debe verificar y proporcionar credenciales correctos para realizar esta acción";
        final String MENSAJE_VERIFICAR_INFORMACION = "Debe verifiar el formato y la información de su solicitud con el formato esperado";
    
    @PostMapping("/login")
    @ResponseBody
    @ApiOperation(value = "Inicio de sesión para conseguir un token de acceso", response = EmpleadosDTO.class, tags = "Seguridad")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest authenticationRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
        }
        try {
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            String token = service.login(authenticationRequest).getJwt();
            if (!token.isBlank()) {
                authenticationResponse.setJwt(token);
                Optional<Empleados> user = service.findByCedula(authenticationRequest.getCedula());
                EmpleadosDTO userDto = MapperUtils.DtoFromEntity(user.get(), EmpleadosDTO.class);
                authenticationResponse.setEmpleado(userDto);
                authenticationResponse.setRol(userDto.getRol());
                return new ResponseEntity(authenticationResponse, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Credenciales invalidos", HttpStatus.UNAUTHORIZED);
            }
        } catch(UsernameNotFoundException | BadCredentialsException ex){
            return new ResponseEntity(MENSAJE_VERIFICAR_CREDENCIALES, HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/sendEmail")
    @ApiOperation(value = "Funcion que permite solicitar una nueva contraseña temporat", tags="Seguridad")
    public ResponseEntity<?> enviarCorreo(@RequestBody EmpleadosDTO empleado){
        try{
            empleado.setSolicitud(true);
            empService.update(empleado, empleado.getId());
            InetAddress ip = InetAddress.getLocalHost();
            Mailer.sendMail("http://"+ip.getHostAddress()+":8989/authentication/cambiarContrasena/"+empleado.getCedula(), empleado.getCorreo());
            return new ResponseEntity<>("Envio del correo en proceso", HttpStatus.OK);
        }catch(UnknownHostException ex){
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("cambiarContrasena/{cedula}")
    public ResponseEntity<?> enviarCorreo(@PathVariable("cedula") String cedula){
        try{
            Optional<Empleados> emp = service.findByCedula(cedula);
            if(emp.isPresent()){
                EmpleadosDTO dto = MapperUtils.DtoFromEntity(emp.get(), EmpleadosDTO.class);
                if(dto.getSolicitud()){
                    String temp = generateTemporalPassword();
                    dto.setPasswordTemporal(Boolean.TRUE);
                    dto.setSolicitud(Boolean.FALSE);
                    dto.setContrasenaEncriptada(temp);
                    empService.update(dto, dto.getId());
                    return new ResponseEntity<>(Mailer.getRespuesta(emp.get().getNombre(), temp), HttpStatus.OK);
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
    
    private String generateTemporalPassword(){
        String tempPassword = "";
        for(int i = 0; i < 25; i++){
            tempPassword += (char) (Math.floor(Math.random()*93) + 33);
        }
        return tempPassword;
    }
    
}
