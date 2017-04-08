package com.sistema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table (name = "tb_funcionario")
@DiscriminatorValue(value = "fun")
@PrimaryKeyJoinColumn(name = "id_funcionario", referencedColumnName = "id_usuario")
@Access(AccessType.FIELD)
public class Funcionario extends Usuario implements Serializable{
    
    
    @Enumerated(EnumType.STRING)
    @Column (name = "enum_especialidadeFuncionario")
    private EspecialidadeFuncionario especialidadeFuncionario;
    
    // Relacionamento ConsultaGeral
    @OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<ConsultaGeral> listaConsultaGeral;

    public List<ConsultaGeral> getListaConsultaGeral() {
        return listaConsultaGeral;
    }

    public void setListaConsultaGeral(List<ConsultaGeral> listaConsultaGeral) {
        this.listaConsultaGeral = listaConsultaGeral;
    }
    
    
    // getters e Setters -----------------------------
    public EspecialidadeFuncionario getEspecialidadeFuncionario() {
        return especialidadeFuncionario;
    }

    public void setEspecialidadeFuncionario(EspecialidadeFuncionario especialidadeFuncionario) {
        this.especialidadeFuncionario = especialidadeFuncionario;
    }
}
