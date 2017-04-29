/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author allanfreitas
 */

@Entity
@Table(name = "tb_consulta_medica")
@DiscriminatorValue(value = "med")
@PrimaryKeyJoinColumn(name = "id_consulta_medica", referencedColumnName = "id_consulta" )
@Access(AccessType.FIELD)
public class ConsultaMedica extends Consulta implements Serializable{
    
    @NotBlank
    @Column(name = "str_diagnostico", nullable = false, length = 400)
    private String diagnostico;
    
    // Relacionamento Pet
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_pet", referencedColumnName = "id_pet")
    private Pet pet;
    
    // Relacionamento Veterinario
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn (name = "fk_veterinario", referencedColumnName = "id_usuario")
    private Veterinario veterinario;
    
    // Relacionamento Exame
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn (name = "fk_exame", referencedColumnName = "id_exame")
    private Exame exame;

    
    
     // getters e Setters -----------------------------
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }   
}
