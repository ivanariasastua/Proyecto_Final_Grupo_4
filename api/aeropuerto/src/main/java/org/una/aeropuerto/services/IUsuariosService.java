/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.entities.Usuarios;

/**
 *
 * @author cordo
 */
public interface IUsuariosService {
    public Optional<List<UsuariosDTO>> findAll();

    public Optional<UsuariosDTO> findById(Long id);
    
    public UsuariosDTO create(UsuariosDTO usuario);

    public Optional<UsuariosDTO> update(UsuariosDTO usuario, Long id);

    public Optional<List<UsuariosDTO>> filtrado(String rol, String nombre, String cedula, boolean estado);

    public Optional<UsuariosDTO> inactivate(Long id);
}
