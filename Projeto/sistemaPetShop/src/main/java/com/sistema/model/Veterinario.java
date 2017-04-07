/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_Veterinario")
@DiscriminatorValue(value = "vet")
@PrimaryKeyJoinColumn(name = "id_veterinario", referencedColumnName = "id_usuario") 
public class Veterinario extends Usuario implements Serializable {

    @Column(name = "str_crmv", nullable = false, length = 60, unique = true)
    private String crmv;

    @Enumerated(EnumType.STRING)
    @Column(name = "str_especialidade", nullable = false, length = 60)
    private String especialidade;

    // Relacionamento ConsultaMedica
    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "fk_consulta", referencedColumnName = "id_consulta_medica")
    private List<ConsultaMedica> ListaConsultaMedica;

    
    // getters e Setters -----------------------------
    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<ConsultaMedica> getListaConsultaMedica() {
        return ListaConsultaMedica;
    }

    public void setListaConsultaMedica(List<ConsultaMedica> ListaConsultaMedica) {
        this.ListaConsultaMedica = ListaConsultaMedica;
    }
    
    
}
