/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.una.aeropuerto.repositories.IEmpleadosRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.entities.Empleados;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.jwt.JwtProvider;


/**
 *
 * @author Ivan Josué Arias Astua
 */
@Service
public class AuthenticationServiceImplementation implements IAuthenticationService{
    
    @Autowired
    private IEmpleadosRepository empleadosRepository;
    
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getCedula(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

        Optional<Empleados> empleado = findByCedula(authenticationRequest.getCedula());

        if (empleado.isPresent()) {
            authenticationResponse.setJwt(jwtProvider.generateToken(authenticationRequest));
            EmpleadosDTO empleadoDto = MapperUtils.DtoFromEntity(empleado.get(), EmpleadosDTO.class);
            authenticationResponse.setEmpleado(empleadoDto);
            RolesDTO rol = MapperUtils.DtoFromEntity(empleado.get().getRol(), RolesDTO.class);
            authenticationResponse.setRol(rol);

            return authenticationResponse;
        } else {
            return null;
        }
    }

    @Override
    public Optional<Empleados> findByCedula(String cedula) {
        return Optional.ofNullable(empleadosRepository.findByCedula(cedula));
    }
}
