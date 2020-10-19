/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.entities.Empleados;
import org.una.aeropuerto.repositories.IEmpleadosRepository;
import org.una.aeropuerto.utils.*;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosServiceImplementation implements IEmpleadosService, UserDetailsService{

    @Autowired
    private IEmpleadosRepository empleadoRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private EmpleadosDTO encriptarPassword(EmpleadosDTO usuario) {
        String password = usuario.getContrasenaEncriptada();
        if (!password.isBlank()) {
            usuario.setContrasenaEncriptada(bCryptPasswordEncoder.encode(password));
        }
        return usuario;
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepository.findById(id), EmpleadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosDTO>> findAll() {
        return ServiceConvertionHelper.findList(empleadoRepository.findAll(), EmpleadosDTO.class);
    }

    @Override
    @Transactional
    public EmpleadosDTO create(EmpleadosDTO empleado) {
        encriptarPassword(empleado);
        Empleados colab = MapperUtils.EntityFromDto(empleado, Empleados.class);
        colab = empleadoRepository.save(colab);
        return MapperUtils.DtoFromEntity(colab, EmpleadosDTO.class);
    }

    @Override
    @Transactional
    public Optional<EmpleadosDTO> update(EmpleadosDTO empleado, Long id) {
        if (empleadoRepository.findById(id).isPresent()) {
            encriptarPassword(empleado);
            Empleados colab = MapperUtils.EntityFromDto(empleado, Empleados.class);
            colab = empleadoRepository.save(colab);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(colab, EmpleadosDTO.class));
        }
        return null;
    }

    @Override
    @Transactional
    public Optional<EmpleadosDTO> inactivate(Long id) {
        empleadoRepository.inactivar(id);
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepository.findById(id), EmpleadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Empleados> empleadoBuscado = findByCedula(username);
        if (empleadoBuscado.isPresent()) {
            Empleados empleado = empleadoBuscado.get();
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_"+empleado.getRol().getNombre()));
            UserDetails userDetails = new User(empleado.getCedula(), empleado.getContrasenaEncriptada(), authorities);
            return userDetails;
        } else {
            System.out.println("loadUserByUserName: fail");
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Empleados> findByCedula(String cedula) {
        return Optional.ofNullable(empleadoRepository.findByCedula(cedula));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosDTO>> findByCedulaAproximate(String cedula) {
        return ServiceConvertionHelper.findList(empleadoRepository.findByCedulaContaining(cedula), EmpleadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosDTO>> findByNombreAproximate(String nombre) {
        return ServiceConvertionHelper.findList(empleadoRepository.findByNombreContaining(nombre), EmpleadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosDTO>> findByAreas(String area) {
        return ServiceConvertionHelper.findList(empleadoRepository.findByAreas(area), EmpleadosDTO.class);
    }

    @Override
    public Optional<List<EmpleadosDTO>> findNoAprobados() {
        return ServiceConvertionHelper.findList(empleadoRepository.findByAprobadoFalseAndEstadoTrue(), EmpleadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosDTO> findByCedulaDTO(String cedula) {
        return Optional.ofNullable(ServiceConvertionHelper.OneToDto(findByCedula(cedula), EmpleadosDTO.class));
    }
}
