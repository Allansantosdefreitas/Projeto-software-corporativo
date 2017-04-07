/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author allanfreitas
 */
@Entity
@Table(name = "tb_consulta_medica" )
public class ConsultaMedica extends Consulta implements Serializable{
    
    @Column(name = "str_diagnostico", nullable = false, length = 400)
    private String diagnostico;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_pet", referencedColumnName = "id_pet")
    private Pet pet;
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn (name = "fk_veterinario", referencedColumnName = "id_usuario")
    private Veterinario veterinario;
    

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }   
}
