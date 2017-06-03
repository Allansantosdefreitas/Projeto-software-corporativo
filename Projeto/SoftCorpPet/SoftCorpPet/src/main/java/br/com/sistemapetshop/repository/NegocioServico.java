/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.repository;

import br.com.sistemapetshop.model.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Usuario
 */
public class NegocioServico {
    
    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistema_pet_pu");

    public static Long inserirServico(Servico servico) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.persist(servico);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }

        return servico.getIdServico();
    }

    public static void atualizarServico(Servico servico) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.merge(servico);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void deletarServico(Servico servico) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            Servico servicoRemove = em.merge(servico);

            et.begin();
            em.remove(servicoRemove);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public static Servico consultarServico(Long idServico) {
        EntityManager em = null;
        Servico servicoResultado = null;

        try {
            em = EMF.createEntityManager();

            servicoResultado = em.find(Servico.class, idServico);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return servicoResultado;
    }

}
