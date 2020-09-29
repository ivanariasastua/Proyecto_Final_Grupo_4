/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ivana
 */
@Entity
@Table(name = "Areas_Trabajos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AreasTrabajos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 35)
    private String nombre;
    
    @Column(length = 100)
    private String descripcion;
    
    @Column
    private boolean estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaTrabajo")
    private List<EmpleadosAreasTrabajos> empleadosAreasTrabajo;
    
    @PrePersist
    public void PrePersist(){
        estado = true;
    }
}
