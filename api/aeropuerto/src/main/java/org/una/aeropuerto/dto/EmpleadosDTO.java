/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author cordo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor 
@ToString
public class EmpleadosDTO {
    
    private Long id;
    private String nombre;
    private String cedula;
    private boolean estado;
    private Boolean esJefe;
    private String contrasenaEncriptada;
    private RolesDTO rol;
    private Boolean passwordTemporal;
    private Boolean solicitud;
    private Boolean aprobado;
    private String correo;
    @JsonManagedReference
    private List<EmpleadosAreasTrabajosDTO> empleadosAreasTrabajo;
    @JsonManagedReference
    private List<EmpleadosHorariosDTO> horarios;
}
