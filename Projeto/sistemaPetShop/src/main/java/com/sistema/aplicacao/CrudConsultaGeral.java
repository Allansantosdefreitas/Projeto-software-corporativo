/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.ConsultaGeral;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan Romualdo
 */
public class CrudConsultaGeral {
    
    private static EntityManagerFactory EMF = null;
    private static EntityManager EM = null;
    private static EntityTransaction et = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ConsultaGeral consulta = new ConsultaGeral();
        
    }
    
    public static void inserirConsulta(ConsultaGeral consulta){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.persist(consulta);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (EM != null) {
                EM.close();
            }
            if (EMF != null) {
                EMF.close();
            }
        }
    }
    
    public static void atualizarConsulta(ConsultaGeral consulta){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.merge(consulta);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (EM != null) {
                EM.close();
            }
            if (EMF != null) {
                EMF.close();
            }
        }
    }

    public static void deletarConsulta(ConsultaGeral consulta){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.remove(consulta);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (EM != null) {
                EM.close();
            }
            if (EMF != null) {
                EMF.close();
            }
        }
        
    }
 
    public static void buscarConsulta(Long idConsultaGeral){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.find(ConsultaGeral.class, idConsultaGeral);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                et.rollback();
            }
        } finally {
            if (EM != null) {
                EM.close();
            }
            if (EMF != null) {
                EMF.close();
            }
        }
    }
    
}
