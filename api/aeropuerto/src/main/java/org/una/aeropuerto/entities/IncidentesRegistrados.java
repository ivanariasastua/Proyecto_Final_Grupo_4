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
    @JoinColumn(name="categoria_id")
    private IncidentesCategorias categoria;
    
    @ManyToOne 
    @JoinColumn(name="emisor_id")
    private Usuarios emisor;
    
    @ManyToOne 
    @JoinColumn(name="responsable_id")
    private Usuarios responsable;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "incidentes_registrados") 
    private List<IncidentesRegistradosEstados> incidentesRegistradosEstados = new ArrayList<>();

}