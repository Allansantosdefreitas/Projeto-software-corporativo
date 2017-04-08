package com.sistema.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_cartao")
@Access(AccessType.FIELD)
public class Cartao implements Serializable {

    @Id
    @Column(name = "id_cartao", unique = true, nullable = false)
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCartao;

    @Column(name = "str_bandeira")
    private String bandeira;

    @Column(name = "str_numero")
    private String numero;

    @Column(name = "date_dataValidade")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataValidade;
    
    // Relacionamento Cliente
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn (name = "fk_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    public Cartao() {
    }

    public Cartao(String bandeira, String numero, Date dataValidade) {
        this.bandeira = bandeira;
        this.numero = numero;
        this.dataValidade = dataValidade;
    }  

    // getters e Setters -----------------------------
    public Long getIdCartao() {
        return idCartao;
    }

    public void setIdCartao(Long idCartao) {
        this.idCartao = idCartao;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
