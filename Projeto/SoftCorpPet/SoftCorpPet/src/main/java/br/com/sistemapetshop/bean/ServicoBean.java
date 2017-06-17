/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Servico;
import br.com.sistemapetshop.repository.NegocioServico;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "ServicoManagedBean")
@SessionScoped
public class ServicoBean {
    
    @EJB
    NegocioServico negocioServico;
    
    Servico servico;
    
    ArrayList<Servico> listaServico;
    
    @PostConstruct
    public void constroi(){
        Servico servico;
    }
    
    
    public List<Servico> listarServicos(){
        
        return negocioServico.listarServicos();
    }
    
    public void adicionar(){
        
        negocioServico.inserirServico(servico);
      
    }
    
    public Servico getServico(){
        return servico;
    }
    
    
    
    
    
    
}
