package com.sistema.sistemapetshop.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table (name = "tb_funcionario")
public class Funcionario extends Usuario implements Serializable{
    
    @Column (name = "enum_especialidadeFuncionario")
    private Enum<EspecialidadeFuncionario> especialidadeFuncionario;
    
    public Enum<EspecialidadeFuncionario> getEspecialidadeFuncionario() {
        return especialidadeFuncionario;
    }

    public void setEspecialidadeFuncionario(Enum<EspecialidadeFuncionario> especialidadeFuncionario) {
        this.especialidadeFuncionario = especialidadeFuncionario;
    }
}
