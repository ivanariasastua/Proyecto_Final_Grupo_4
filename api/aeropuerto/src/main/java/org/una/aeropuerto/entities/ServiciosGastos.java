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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author cordo
 */
@Entity
@Table(name = "Servicios_Gastos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ServiciosGastos implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "servicio")
    private Servicios servicio;
    
    @Column(name = "empresa", length = 50)
    private String empresa;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Setter(AccessLevel.NONE)
    private Date fechaRegistro;
    
    @Column(columnDefinition="TINYINT")
    private boolean estadoPago;
    
    @Column(columnDefinition="TINYINT")
    private boolean estadoGasto;
    
    @Column(length = 50)
    private String numeroContrato;
    
    @Column
    private Integer perioricidad;
    
    @Column
    private Long duracion;
    
    @ManyToOne 
    @JoinColumn(name="responsable")
    private Empleados responsable;
    
    @Column
    private boolean estado;
    
    @Column
    private String observaciones;
    
    @PrePersist
    public void PrePersist(){
        estado = true;
        fechaRegistro = new Date();
    }
    
}
