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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author allanfreitas
 */
@Entity
@Table(name = "tb_consulta_geral")
@DiscriminatorValue(value = "ger")
@PrimaryKeyJoinColumn(name = "id_consulta_geral", referencedColumnName = "id_consulta") 
@Access(AccessType.FIELD)
public class ConsultaGeral extends Consulta implements Serializable{
    
    
    // Relacionamento Servico
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_servico", referencedColumnName = "id_servico")
    private Servico servico;
    
    // Relacionamento Funcionario
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "fk_funcionario", referencedColumnName = "id_funcionario")
    private Funcionario funcionario;
    
    
}
