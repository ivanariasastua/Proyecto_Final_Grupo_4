/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author cordo
 */
@Entity
@Table(name = "Incidentes_Registrados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncidentesRegistrados implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descripcion", length = 100)
    private String descripcion;
    
    @ManyToOne 
    @JoinColumn(name="categoria")
    private IncidentesCategorias categoria;
    
    @ManyToOne 
    @JoinColumn(name="emisor")
    private Empleados emisor;
    
    @ManyToOne 
    @JoinColumn(name="responsable")
    private Empleados responsable;

    @Column(columnDefinition="TINYINT")
    public Boolean estado;
    
    @ManyToOne 
    @JoinColumn(name="area_trabajo")
    private AreasTrabajos areaTrabajo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incidenteRegistrado") 
    private List<IncidentesRegistradosEstados> incidentesRegistradosEstados = new ArrayList<>();

    @PrePersist
    public void PrePersist(){
        this.estado = true;
    }
}
