/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.repository;

import br.com.sistemapetshop.model.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioServico {

    
    EntityManager et;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirServico(Servico servico) {
        
        et.persist(servico);

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarServico(Servico servico) {

        et.merge(servico);
        
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarServico(Servico servico) {
        
        
        et.remove(et);

    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Servico consultarServico(Long idServico) {
        
        return et.find(Servico.class, idServico);
        
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Servico> listarServicos() {
        List<Servico> listaServico;
        
        listaServico = et.createQuery("s FROM Servico s").getResultList();
        
        return listaServico;
        
    }

}
