/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import br.com.sistemapetshop.model.Usuario;
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
public class NegocioUsuario {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarUsuario(Usuario usuario) {
        em.merge(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarUsuario(Usuario usuario) {
        em.remove(usuario);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public Usuario consultarUsuario(Long idUsuario) {
        return em.find(Usuario.class, idUsuario);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Usuario> listarUsuarios() {

        return (List<Usuario>) em.createQuery("From Usuario u").getResultList();
    }

}
