/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Exame;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Henrique
 */
public class CrudExame {

    private static EntityManagerFactory EMF = null;
    private static EntityManager EM = null;
    private static EntityTransaction et = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Exame exame = new Exame();
        
    }
    
    public static void inserirExame(Exame exame){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.persist(exame);
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
    
    public static void atualizarExame(Exame exame){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.merge(exame);
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

    public static void deletarExame(Exame exame){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.remove(exame);
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
 
    public static void buscarExame(Long idExame){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.find(Exame.class, idExame);
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
