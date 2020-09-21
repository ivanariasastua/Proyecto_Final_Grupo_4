/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Optional;
import org.una.aeropuerto.dto.AuthenticationRequest;
import org.una.aeropuerto.dto.AuthenticationResponse;
import org.una.aeropuerto.entities.Empleados;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public interface IAuthenticationService {
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest); 
    public Optional<Empleados> findByCedula(String cedula);
}
