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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
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
@Table(name = "Parametros_Sistema")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParametrosSistema implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "valor",length = 50)
    private String valor;
    
    @Column(name = "descripcion" ,length = 100)
    private String descripcion;
    
    @Column(columnDefinition="TINYINT")
    private boolean estado;
    
    @Column(name = "codigo_identificador", length = 25)
    private String codigoIdentificador;
    
    @Column(name = "fecha_registro", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    
    @PrePersist
    public void PrePersist(){
        estado = true;
        fechaModificacion = new Date();
        fechaRegistro = new Date();
    }
    
    @PreUpdate
    public void PreUpdate(){
        fechaModificacion = new Date();
    }
}
