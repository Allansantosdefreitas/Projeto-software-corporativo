/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Endereco;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Luis Henrique
 */
public class CrudEndereco {

    private static EntityManagerFactory EMF = null;
    private static EntityManager EM = null;
    private static EntityTransaction et = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Endereco endereco = new Endereco();
        
    }
    
    public static void inserirEndereco(Endereco endereco){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.persist(endereco);
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
    
    public static void atualizarEndereco(Endereco endereco){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.merge(endereco);
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

    public static void deletarEndereco(Endereco endereco){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.remove(endereco);
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
 
    public static void consultarEndereco(Long idEndereco){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.find(Endereco.class, idEndereco);
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
