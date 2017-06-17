/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Servico;
import br.com.sistemapetshop.repository.NegocioServico;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jonathan Romualdo
 */
@ManagedBean(name = "ServicoManagedBean")
@SessionScoped
public class ServicoBean {

    @EJB
    NegocioServico negocioServico;
    
    Servico servico;
    
    @PostConstruct
    public void contruir(){
        servico = new Servico();
    }
    
    public void adicionar(){
        negocioServico.inserirServico(servico);
    }
    
    public void atualizar(){
        negocioServico.atualizarServico(servico);
    }
    
    public void remover(){
        negocioServico.deletarServico(servico);
    }
    
    public void buscar(){
        negocioServico.consultarServico(servico.getIdServico());
    }
    
}
