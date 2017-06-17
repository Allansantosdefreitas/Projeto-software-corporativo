package br.com.sistemapetshop.repository;

import br.com.sistemapetshop.model.Veterinario;
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
public class NegocioVeterinario {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirVeterinario(Veterinario veterinario) {
        em.persist(veterinario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarVeterinario(Veterinario veterinario) {
        em.merge(veterinario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarVeterinario(Veterinario veterinario) {
        em.remove(veterinario);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Veterinario consultarVeterinario(Long idUsuario) {
        return em.find(Veterinario.class, idUsuario);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Veterinario> listarVeterinario() {

        return (List<Veterinario>) em.createQuery("From Veterinario s").getResultList();
    }
    
}
