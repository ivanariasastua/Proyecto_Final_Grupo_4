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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "Empleados")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Empleados implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 50)
    private String nombre;
    
    @Column(length = 15)
    private String cedula;
    
    @Column
    private Boolean estado;
    
    @ManyToOne
    @JoinColumn(name="jefe")
    private Empleados jefe;
    
    @Column(name = "contrasena_encriptada", length = 100)
    private String contrasenaEncriptada;
    
    @ManyToOne 
    @JoinColumn(name="rol")
    private Roles rol;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jefe")
    private List<Empleados> empleados;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaTrabajo")
    private List<EmpleadosAreasTrabajos> empleadosAreasTrabajo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleado")
    private List<EmpleadosHorarios> horarios;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emisor")
    private List<IncidentesRegistrados> incidentesEmitidos;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable")
    private List<IncidentesRegistrados> incidentesResponsable;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable")
    private List<ServiciosGastos> gastosResponsable;
    
    @PrePersist
    public void prePersist() {
        estado = true;
    }
    
}
