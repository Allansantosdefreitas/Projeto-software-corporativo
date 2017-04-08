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
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_exame")
@Access(AccessType.FIELD)
public class Exame implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exame", nullable = false)
    private Long idExame;

    @Column(name = "str_nome", length = 60, unique = false)
    private String nome;

    @Column(name = "str_tipo", length = 60, unique = false)
    private String tipo;

    @Column(name = "str_descricao", length = 100, unique = false)
    private String descricao;

    @Column(name = "dbl_valor", unique = false)
    private Double valor;

    // Relacionamento ConsultaMedica
    @OneToMany(mappedBy = "exame", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = false)
    @JoinColumn(name = "fk_consulta_medica", referencedColumnName = "id_consulta_medica")
    private List<ConsultaMedica> listaConsultaMedica;

    
    public Exame(){
        
    }
    
    public Exame(String nome, String tipo, String descricao, Double valor, List<ConsultaMedica> listaConsultaMedica) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.listaConsultaMedica = listaConsultaMedica;
    }

    // getters e Setters -----------------------------
    public Long getIdExame() {
        return idExame;
    }

    public void setIdExame(Long idExame) {
        this.idExame = idExame;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<ConsultaMedica> getListaConsultaMedica() {
        return listaConsultaMedica;
    }

    public void setListaConsultaMedica(List<ConsultaMedica> listaConsultaMedica) {
        this.listaConsultaMedica = listaConsultaMedica;
    }

}
