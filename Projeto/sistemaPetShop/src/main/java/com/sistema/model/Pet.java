/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_animal")
public class Pet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lon_id")
    private Long idPet;

    @Column(name = "str_nome", nullable = true, length = 60)
    private String nome;

    @Column(name = "flt_peso", nullable = false)
    private Float peso;

    @Column(name = "str_raca", nullable = false, length = 60)
    private String raca;

    @Column(name = "boo_pedegree", nullable = false)
    private Boolean pedegree;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn (name = "ext_IdUsuario", referencedColumnName = "lon_id")
    private Cliente cliente;
    
    @OneToMany (mappedBy = "pet", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn (name = "ext_IdConsulta", referencedColumnName = "lon_id")
    private List<Consulta> consulta;

    public Long getIdPet() {
        return idPet;
    }

    public void setIdPet(Long idPet) {
        this.idPet = idPet;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Boolean getPedegree() {
        return pedegree;
    }

    public void setPedegree(Boolean pedegree) {
        this.pedegree = pedegree;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Consulta> getConsulta() {
        return consulta;
    }

    public void setConsulta(List<Consulta> consulta) {
        this.consulta = consulta;
    }
}
