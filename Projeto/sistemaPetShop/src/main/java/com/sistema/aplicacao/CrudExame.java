/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.ConsultaMedica;
import com.sistema.model.Exame;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Henrique
 */
public class CrudExame {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<ConsultaMedica> listaConsultaMedica = new ArrayList<ConsultaMedica>();
        Exame exame = new Exame("Ultrasonografia", "teste", "radiografia em geral", 200.00, listaConsultaMedica);
        
        // Inserir ----------------------- OK
        inserirExame(exame);
  
        //Consultar --------------------- OK
        Exame exameResultado = consultarExame(Long.parseLong("2"));
          
        System.out.println("Nome: " +  exameResultado.getNome() );
        System.out.println("Tipo: " + exameResultado.getTipo() );
        System.out.println("Descrição: " + exameResultado.getDescricao() );
        System.out.println("Valor: " + exameResultado.getValor() );
        
        //Atualizar ----------------------------- OK
        System.out.println("atualizar");
        exameResultado.setValor(600.00);
        atualizarExame(exameResultado);
        System.out.println("Valor: " + exameResultado.getValor() );
        
        // Deletar ------------------------------ NOK
        deletarExame(exameResultado);
        
    }
    
    public static void inserirExame(Exame exame){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.persist(exame);
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
    
    public static void atualizarExame(Exame exame){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.merge(exame);
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
      

    public static void deletarExame(Exame exame){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.remove(exame);
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
 
    public static Exame consultarExame(Long idExame){
        EntityManager em = null;
        Exame exameResultado = null;
        try {
            em = EMF.createEntityManager();

            exameResultado = em.find(Exame.class, idExame);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return exameResultado;
    }
     
    
    
}
