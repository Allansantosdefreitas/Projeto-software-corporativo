/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

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
 * @author Jonathan Romualdo
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioPet {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirServico(Pet pet) {
        em.persist(pet);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarServico(Pet pet) {
        em.merge(pet);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarServico(Pet pet) {
        em.remove(em);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Pet consultarServico(Long idPet) {
        return em.find(Pet.class, idPet);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Pet> listarServicos() {

        return (List<Pet>) em.createQuery("From Servico s").getResultList();
    }

}
