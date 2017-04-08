/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Romauldo
 */
@Entity
@Table(name = "tb_servico")
@Access(AccessType.FIELD)
public class Servico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico", nullable = false, unique = true)
    private Long idServico;

    @Column(name = "str_nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "dbl_valor", nullable = false, unique = true)
    private Double valor;
    
    // Relacionamento Consulta
    @OneToMany (mappedBy = "servico", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    //@JoinColumn (name = "fk_consulta_geral", referencedColumnName = "id_consulta_geral")
<<<<<<< HEAD
    private List<ConsultaGeral> listaConsulta;
=======
    private List<ConsultaGeral> listaConsultaGeral;
>>>>>>> cbdc9e26fd2f91ec9254d60c762805dcc90dc2a7

    
    // getters e Setters -----------------------------
    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

<<<<<<< HEAD
    public List<ConsultaGeral> getListaConsulta() {
        return listaConsulta;
    }

    public void setListaConsulta(List<ConsultaGeral> listaConsulta) {
        this.listaConsulta = listaConsulta;
=======
    public List<ConsultaGeral> getListaConsultaGeral() {
        return listaConsultaGeral;
    }

    public void setListaConsultaGeral(List<ConsultaGeral> listaConsultaGeral) {
        this.listaConsultaGeral = listaConsultaGeral;
>>>>>>> cbdc9e26fd2f91ec9254d60c762805dcc90dc2a7
    }


    
}
