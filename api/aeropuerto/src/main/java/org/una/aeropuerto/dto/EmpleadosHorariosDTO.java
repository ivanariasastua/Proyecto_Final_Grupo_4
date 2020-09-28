/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import java.util.ArrayList;
import java.util.Date;
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
public class EmpleadosHorariosDTO {
    
    private Long id;
    private EmpleadosDTO empleado;
    private Date horaEntrada;
    private Date horaSalida;
    private String diaEntrada;
    private String diaSalida;
    private boolean estado;
    @ToString.Exclude
    private List<EmpleadosMarcajesDTO> empleadosMarcajes=new ArrayList<>();
}
