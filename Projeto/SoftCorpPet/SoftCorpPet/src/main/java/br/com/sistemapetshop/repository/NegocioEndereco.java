/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.repository;

import br.com.sistemapetshop.model.Endereco;
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
 * @author Usuario
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioEndereco {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirEndereco(Endereco endereco) {
        em.persist(endereco);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarEndereco(Endereco endereco) {
        em.merge(endereco);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarEndereco(Endereco endereco) {
        em.remove(endereco);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Servico consultarEndereco(Long idEndereco) {
        return em.find(Servico.class, idEndereco);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Endereco> listarEnderecos() {

        return (List<Endereco>) em.createQuery("From Endereco s").getResultList();
    }
}
