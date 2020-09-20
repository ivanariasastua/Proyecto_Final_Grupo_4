/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.UsuariosDTO;
import org.una.aeropuerto.entities.Usuarios;
import org.una.aeropuerto.repositories.IUsuariosRepository;
import org.una.aeropuerto.utils.*;

/**
 *
 * @author cordo
 */
@Service
public class UsuariosServiceImplementation implements IUsuariosService {

    @Autowired
    private IUsuariosRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<UsuariosDTO>> findAll() {
        return ServiceConvertionHelper.findList(usuarioRepository.findAll(), UsuariosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuariosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(usuarioRepository.findById(id), UsuariosDTO.class);
    }

    @Override
    @Transactional
    public UsuariosDTO create(UsuariosDTO usuario) {
        Usuarios usu = MapperUtils.EntityFromDto(usuario, Usuarios.class);
        usu = usuarioRepository.save(usu);
        return MapperUtils.DtoFromEntity(usu, UsuariosDTO.class);
    }

    @Override
    @Transactional
    public Optional<UsuariosDTO> update(UsuariosDTO usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            Usuarios usu = MapperUtils.EntityFromDto(usuario, Usuarios.class);
            usu = usuarioRepository.save(usu);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(usu, UsuariosDTO.class));
        } 
        return null;
    }

    @Override
    public Optional<List<UsuariosDTO>> filtrado(String rol, String nombre, String cedula, boolean estado) {
        return ServiceConvertionHelper.findList(usuarioRepository.findFilter(rol, nombre, cedula, estado), UsuariosDTO.class);
    }

    @Override
    public Optional<UsuariosDTO> inactivate(Long id) {
        usuarioRepository.inactivar(id);
        return ServiceConvertionHelper.oneToOptionalDto(usuarioRepository.findById(id), UsuariosDTO.class);
    }
}
