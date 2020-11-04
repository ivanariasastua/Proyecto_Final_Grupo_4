/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.json.bind.annotation.JsonbDateFormat;
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
public class IncidentesRegistradosDTO {
    private Long id;
    private String descripcion;
    //@JsonBackReference
    private IncidentesCategoriasDTO categoria;
    public Boolean estado;
   // @JsonBackReference
    private EmpleadosDTO emisor;
   // @JsonBackReference
    private EmpleadosDTO responsable;
    private AreasTrabajosDTO areaTrabajo;
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaRegistro;
    @JsonManagedReference
    private List<IncidentesRegistradosEstadosDTO> incidentesRegistradosEstados = new ArrayList<>();
}
