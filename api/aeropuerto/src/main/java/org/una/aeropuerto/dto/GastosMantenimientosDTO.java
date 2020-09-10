/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aeropuerto.entities.Servicios;
import org.una.aeropuerto.entities.Usuarios;

/**
 *
 * @author cordo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GastosMantenimientosDTO {

    private Long id;
    private Servicios servicio;
    private String empresa;
    private Date fechaRegistro;
    private boolean estadoPago;
    private boolean estadoGasto;
    private String numeroContrato;
    private boolean perioricidad;
    private Usuarios responsable;

}
