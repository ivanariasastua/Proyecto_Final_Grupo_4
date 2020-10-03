/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
/**
 *
 * @author ivana
 */

@Entity
@Table(name = "Empleados_Horarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpleadosHorarios implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="empleado")
    private Empleados empleado;
    
    @Column(name = "hora_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEntrada;
    
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSalida;
    
    @Column(length = 9)
    private String diaEntrada;
    
    @Column
    private String diaSalida;
    
    @Column
    private Boolean estado;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadoHorario")
    private List<EmpleadosMarcajes> empleadosMarcajes;
    
    @PrePersist
    public void PrePersist(){
        estado = true;
    }
}
