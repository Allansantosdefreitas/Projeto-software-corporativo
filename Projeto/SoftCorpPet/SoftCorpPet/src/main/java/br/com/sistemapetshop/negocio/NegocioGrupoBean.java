/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import br.com.sistemapetshop.model.Grupo;
import br.com.sistemapetshop.model.Servico;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author allanfreitas
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)

public class NegocioGrupoBean {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Grupo> listarGrupos() {

        return (List<Grupo>) em.createQuery("From Grupo g").getResultList();
    }
    
    public Grupo getGrupo(String nomeGrupo, String[] grupos){
        TypedQuery<Grupo> query = em.createNamedQuery(nomeGrupo, Grupo.class);
        
        int i = 1;
        for(String grupo : grupos ){
            query.setParameter(i++, grupo);
        }
        
        return query.getSingleResult();
        
    }
    
    
    
}
