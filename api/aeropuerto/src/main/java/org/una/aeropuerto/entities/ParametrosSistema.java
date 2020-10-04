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
    
    @PrePersist
    public void PrePersist(){
        estado = true;
    }
}
