package com.sistema.sistemapetshop.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_cartao")
public class Cartao implements Serializable {

    @Id
    @Column(name = "long_idCartao")
    private Long idCartao;

    @Column(name = "str_bandeira")
    private String bandeira;

    @Column(name = "str_numero")
    private String numero;

    @Column(name = "date_dataValidade")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataValidade;

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
}
