/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import br.com.sistemapetshop.model.Servico;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jonathan Romualdo
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioServico {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirServico(Servico servico) {
        em.persist(servico);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarServico(Servico servico) {
        em.merge(servico);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarServico(Servico servico) {
        em.remove(em);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Servico consultarServico(Long idServico) {
        return em.find(Servico.class, idServico);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Servico> listarServicos() {

        return (List<Servico>) em.createQuery("From Servico s").getResultList();
    }

}
