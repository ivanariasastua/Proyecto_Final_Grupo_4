/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.entities.ParametrosSistema;
import org.una.aeropuerto.repositories.IParametrosSistemaRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class ParametrosSistemaServiceImplementation implements IParametrosSistemaService {

    @Autowired
    private IParametrosSistemaRepository parametrosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosSistemaDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(parametrosRepository.findById(id), ParametrosSistemaDTO.class);
    }

    @Override
    @Transactional
    public ParametrosSistemaDTO create(ParametrosSistemaDTO parametrosSistema) {
        ParametrosSistema param = MapperUtils.EntityFromDto(parametrosSistema, ParametrosSistema.class);
        param = parametrosRepository.save(param);
        return MapperUtils.DtoFromEntity(param, ParametrosSistemaDTO.class);
    }

    @Override
    @Transactional
    public Optional<ParametrosSistemaDTO> update(ParametrosSistemaDTO parametrosSistema, Long id) {
        if (parametrosRepository.findById(id).isPresent()) {
            ParametrosSistema param = MapperUtils.EntityFromDto(parametrosSistema, ParametrosSistema.class);
            param = parametrosRepository.save(param);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(param, ParametrosSistemaDTO.class));
        }
        return null;
    }

    @Override
    public Optional<ParametrosSistemaDTO> findByCodigoIdentificador(String codigo) {
        return ServiceConvertionHelper.oneToOptionalDto(parametrosRepository.findByCodigoIdentificador(codigo), ParametrosSistemaDTO.class);
    }

    @Override
    public Optional<List<ParametrosSistemaDTO>> findByFechaRegistro(Date fecha1, Date fecha2) {
        return ServiceConvertionHelper.findList(parametrosRepository.findByFechaRegistroBetween(fecha1, fecha2), ParametrosSistemaDTO.class);
    }

    @Override
    public Optional<List<ParametrosSistemaDTO>> findByFechaModificacion(Date fecha1, Date fech2) {
        return ServiceConvertionHelper.findList(parametrosRepository.findByFechaModificacionBetween(fecha1, fech2), ParametrosSistemaDTO.class);
    }

    @Override
    public Optional<ParametrosSistemaDTO> findByValor(String valor) {
        return ServiceConvertionHelper.oneToOptionalDto(parametrosRepository.findByValor(valor), ParametrosSistemaDTO.class);
    }
}
