/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import br.com.sistemapetshop.model.Funcionario;
import java.io.Serializable;
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
 * @author Usuario
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioFuncionario implements Serializable {
    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirServico(Funcionario funcionario) {
        em.persist(funcionario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarServico(Funcionario funcionario) {
        em.merge(funcionario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarServico(Funcionario funcionario) {
        em.remove(funcionario);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Funcionario consultarServico(Long idFuncionario) {
        return em.find(Funcionario.class, idFuncionario);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Funcionario> listarServicos() {

        return (List<Funcionario>) em.createQuery("From Funcionario f").getResultList();
    }
}
