/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.utils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author ivana
 */
public class ServiceConvertionHelper {
    
    
    public static<D, E> Optional<List<D>> findList(final Collection<E> list, Class<D> dtoClass){
        if(list != null){
            List<D> lista = MapperUtils.DtoListFromEntityList(list, dtoClass);
            return Optional.ofNullable(lista);
        }
        return null;
    }
    /*
    private Optional<List<UsuarioDTO>> findList(List<Usuario> list) {
        if (list != null) {
            List<UsuarioDTO> usuariosDTO = MapperUtils.DtoListFromEntityList(list, UsuarioDTO.class);
            return Optional.ofNullable(usuariosDTO);
        } else {
            return null;
        }
    }*/
    
    public static<D, E> Optional<List<D>> findList(final Optional<Collection<E>> list, Class<D> dtoClass){
        if(list.isPresent()){
            return findList(list.get(), dtoClass);
        }
        return null;
    }
    
/*
    private Optional<List<UsuarioDTO>> findList(Optional<List<Usuario>> list) {
        if (list.isPresent()) {
            return findList(list.get());
        } else {
            return null;
        }
    }
*/
    public static<D, E> Optional<D> oneToOptionalDto(final Optional<E> one, Class<D> dtoClass){
        if(one.isPresent()){
            D oneDto = MapperUtils.DtoFromEntity(one.get(), dtoClass);
            return Optional.ofNullable(oneDto);
        }
        return null;
    }
/*
    private Optional<UsuarioDTO> oneToDto(Optional<Usuario> one) {
        if (one.isPresent()) {
            UsuarioDTO usuarioDTO = MapperUtils.DtoFromEntity(one.get(), UsuarioDTO.class);
            return Optional.ofNullable(usuarioDTO);
        } else {
            return null;
        }
    }*/
    
    public static<D, E> D oneToDto(final Optional<E> one, Class<D> dtoClass){
        if(one.isPresent()){
            return MapperUtils.DtoFromEntity(one.get(), dtoClass);
        }
        return null;
    }
}
