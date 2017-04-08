/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.ConsultaGeral;
import com.sistema.model.EspecialidadeFuncionario;
import com.sistema.model.Funcionario;
import com.sistema.model.Servico;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan Romualdo
 */
public class CrudConsultaGeral {
    
    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ConsultaGeral consultaGeral = new ConsultaGeral();
        
        
        // Inserir ------------------------------------ OK
        // Serviço  
        Servico servico = new Servico();
        servico.setNome("Tosa");
        servico.setValor(300.00);
        
        // Funcionário
        Funcionario funcionario = new Funcionario();
        funcionario.setEspecialidadeFuncionario(EspecialidadeFuncionario.TOSADOR);
        funcionario.setNome("João das Nevis");
        funcionario.setEmail("joao@gmail.com");
        funcionario.setLogin("joaonevis7");
        funcionario.setSenha("12345678");
        
        //consultaGeral.setDataMarcada(dataMarcada);
        consultaGeral.setServico(servico);
        consultaGeral.setFuncionario(funcionario);
        
        //inserirConsulta(consultaGeral);
        
        // Consultar --------------------------------------- falta sobrescrever o toString
        ConsultaGeral consultaGeralResultado = buscarConsulta(Long.parseLong("1") );

        System.out.println("Funcionario: " + consultaGeralResultado.getFuncionario() );
        System.out.println("Serviço: " + consultaGeralResultado.getServico() );
      
        // Atualizar ----- parei aqui
        
        
        
        
    }
    
    public static void inserirConsulta(ConsultaGeral consulta){  
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.persist(consulta);
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
    
    public static void atualizarConsulta(ConsultaGeral consulta){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.merge(consulta);
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

    public static void deletarConsulta(ConsultaGeral consulta){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.remove(consulta);
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
 
    public static ConsultaGeral buscarConsulta(Long idConsultaGeral){
        EntityManager em = null;
        ConsultaGeral consultaGeralResultado = null;
        
        try {
            em = EMF.createEntityManager();
            
            consultaGeralResultado = em.find(ConsultaGeral.class, idConsultaGeral);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return consultaGeralResultado;
    }
    
}
