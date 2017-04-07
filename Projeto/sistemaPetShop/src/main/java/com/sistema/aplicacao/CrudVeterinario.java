/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.aplicacao;

import com.sistema.model.Veterinario;
import com.sistema.model.Consulta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Luis Henrique
 */
public class CrudVeterinario {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Veterinario vet = new Veterinario();
        
        List<Consulta> listaConsultas = null;
        
        //terminar inserir
        
        //consultar
        
        //atualizar
        
        //deletar
        
        //consultar
    }
    
    public static void inserirVeterinario(Veterinario veterinario){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.persist(veterinario);
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
    
    public static void atualizarVeterinario(Veterinario veterinario){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.merge(veterinario);
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

    public static void deletarVeterinario(Veterinario veterinario){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.remove(veterinario);
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
 
    public static void consultarVeterinario(Long idVeterinario){
        EntityManager em = null;

        try {
            em = EMF.createEntityManager();

            em.find(Veterinario.class, idVeterinario);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
}
