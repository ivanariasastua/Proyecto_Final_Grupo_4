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
import javax.persistence.OneToMany;
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
@Table(name = "Servicios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 25)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio") 
    private List<ServiciosPrecios> serviciosPrecios = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio") 
    private List<ServiciosGastos> serviciosGastos = new ArrayList<>();


}
