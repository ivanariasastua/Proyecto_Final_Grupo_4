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
import javax.persistence.FetchType;
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
    
    @Column(length = 15, unique = true)
    private String cedula;
    
    @Column(columnDefinition="TINYINT")
    private Boolean estado;
    
    @Column(columnDefinition="TINYINT")
    private Boolean aprobado;
    
    @Column(columnDefinition="TINYINT")
    private Boolean solicitud;
    
    @Column(name="password_temporal", columnDefinition="TINYINT")
    private Boolean passwordTemporal;
    
    @Column(length = 50, unique = true)
    private String correo;
    
    @Column(name="es_jefe", columnDefinition="TINYINT")
    private Boolean esJefe;
    
    @Column(name = "contrasena_encriptada", length = 30)
    private String contrasenaEncriptada;
    
    @ManyToOne 
    @JoinColumn(name="rol")
    private Roles rol;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaTrabajo")
    private List<EmpleadosAreasTrabajos> empleadosAreasTrabajo;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER ,mappedBy = "empleado")
    private List<EmpleadosHorarios> horarios;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emisor")
//    private List<IncidentesRegistrados> incidentesEmitidos;
//    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable")
//    private List<IncidentesRegistrados> incidentesResponsable;
//    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "responsable")
    private List<ServiciosGastos> gastosResponsable;
    
    @PrePersist
    public void prePersist() {
        estado = true;
        aprobado = false;
        passwordTemporal = false;
        esJefe = false;
        solicitud = false;
    }
    
}
