/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Empleados_Areas_Trabajo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpleadosAreasTrabajos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="empleado")
    private Empleados empleado;
    
    @ManyToOne 
    @JoinColumn(name="area_trabajo")
    private AreasTrabajos areaTrabajo;
    
    @Column(columnDefinition="TINYINT")
    private boolean estado;
    
    @PrePersist
    public void PrePersist(){
        this.estado = true;
    }
 
}
