/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author ivana
 */
@Entity
@Table(name = "Empleados_Marcajes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpleadosMarcajes implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name="empleado_horario")
    private EmpleadosHorarios empleadoHorario;
    
    @Column(name = "hora_entrada")
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    
    @Column(name = "hora_salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistros;
    
    @PrePersist
    public void PrePersist(){
        fechaRegistros = new Date();
    }
}
