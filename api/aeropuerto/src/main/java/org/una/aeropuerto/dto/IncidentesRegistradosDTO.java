/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.una.aeropuerto.entities.IncidentesCategorias;
import org.una.aeropuerto.entities.Usuarios;

/**
 *
 * @author cordo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncidentesRegistradosDTO {
    private Long id;
    private String descripcion;
    private IncidentesCategorias categoria;
    private Usuarios emisor;
    private Usuarios responsable;
    private List<IncidentesRegistradosEstadosDTO> incidentesRegistradosEstados = new ArrayList<>();
}
