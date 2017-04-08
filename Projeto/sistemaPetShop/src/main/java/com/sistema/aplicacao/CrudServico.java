/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.aplicacao;

import com.sistema.model.ConsultaGeral;
import com.sistema.model.Servico;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Luis Henrique
 */
public class CrudServico {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Servico servico = new Servico();
        servico.setNome("Banho");
        servico.setValor(100.00);
        
        List<ConsultaGeral> listaConsultaGeral = new ArrayList<ConsultaGeral>();
        servico.setListaConsultaGeral(listaConsultaGeral);
        
        //inserir ------------------------ OK
        inserirServico(servico);
        
        //consultar ------------------- OK
        Servico servicoResultado = consultarServico(Long.parseLong("1"));
         
        System.out.println("Nome: " + servicoResultado.getNome() );
        System.out.println("Valor: " + servicoResultado.getValor());
        
        //atualizar --------------------- OK
        servicoResultado.setNome("Tosa");
        servicoResultado.setValor(100.00);
        atualizarServico(servicoResultado);
        
        //remover -------------------------- NOK
        deletarServico(servicoResultado);
        
    }
    
    public static void inserirServico(Servico servico){
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
    }
    
    public static void atualizarServico(Servico servico){
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

    public static void deletarServico(Servico servico){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.remove(servico);
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
 
    public static Servico consultarServico(Long idServico){
        EntityManager em = null;
        EntityTransaction et = null;
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
