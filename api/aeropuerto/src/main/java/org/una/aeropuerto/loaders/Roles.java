/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.loaders;

/**
 *
 * @author Ivan Josué Arias Astua
 */
public enum Roles {
    ADMINISTRADOR("ADMINISTRADOR"),
    GESTOR("GESTOR"),
    AUDITOR("AUDITOR"),
    GERENTE("GERENTE");
    
    private String nombre;

    Roles(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
