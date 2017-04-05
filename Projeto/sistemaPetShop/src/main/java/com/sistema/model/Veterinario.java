/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_Veterinario")
public class Veterinario extends Usuario implements Serializable {

    @Column(name = "str_crmv", nullable = false, length = 60, unique = true)
    private String crmv;

    @Column(name = "str_especialidade", nullable = false, length = 60)
    private String especialidade;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "ext_IdConsulta", referencedColumnName = "lon_id")
    private List<Consulta> consulta;

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

    public List<Consulta> getConsulta() {
        return consulta;
    }

    public void setConsulta(List<Consulta> consulta) {
        this.consulta = consulta;
    }

}
