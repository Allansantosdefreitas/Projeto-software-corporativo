/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.repository;

import br.com.sistemapetshop.model.Exame;
import br.com.sistemapetshop.model.Pet;
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
 * @author Jonathn Romualdo
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioExame {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirServico(Exame exame) {
        em.persist(exame);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarServico(Exame exame) {
        em.merge(exame);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarServico(Exame exame) {
        em.remove(em);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Exame consultarServico(Long idExame) {
        return em.find(Exame.class, idExame);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Exame> listarServicos() {

        return (List<Exame>) em.createQuery("From Servico s").getResultList();
    }
}
