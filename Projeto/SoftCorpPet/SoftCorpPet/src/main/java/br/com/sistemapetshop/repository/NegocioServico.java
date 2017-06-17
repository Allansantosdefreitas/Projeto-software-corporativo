/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.repository;

import br.com.sistemapetshop.model.Servico;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Jonathan Romualdo
 */
@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioServico {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Long inserirServico(Servico servico) {
        em.persist(servico);
        return servico.getIdServico();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarServico(Servico servico) {
        em.merge(servico);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarServico(Servico servico) {
        em.merge(servico);
        em.remove(servico);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Servico consultarServico(Long idServico) {
        return em.find(Servico.class, idServico);
    }

}
