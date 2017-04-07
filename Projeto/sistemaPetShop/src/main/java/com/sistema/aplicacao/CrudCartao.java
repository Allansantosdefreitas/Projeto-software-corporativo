/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Cartao;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan Romualdo
 */
public class CrudCartao {
    
    private static EntityManagerFactory EMF = null;
    private static EntityManager EM = null;
    private static EntityTransaction et = null;
    
    public static void main(String[] args){
        Calendar calendario = Calendar.getInstance();
        calendario.set(2018, 7, 17);
        
        Cartao cartao = new Cartao("Visa", "1234568790123456", calendario.getTime());
        
        inserirCartao(cartao);
        
        Cartao cartaoResultado = consultarCartao(Long.parseLong("1"));
        
        System.out.println("Bandeira: " + cartaoResultado.getBandeira());
        System.out.println("Numero: " + cartaoResultado.getNumero());
        System.out.println("Validade: " + cartaoResultado.getDataValidade());
        
        //atualizarCartao(cartao);
        
        cartao.setBandeira("Master");
        
        atualizarCartao(cartao);     
        
        System.out.println("Bandeira: " + cartaoResultado.getBandeira());
        
        //deletarCartao(cartao);
        
        deletarCartao(cartao);
        
        
    }
    
    public static void inserirCartao(Cartao cartao){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.persist(cartao);
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
    
    public static void atualizarCartao(Cartao cartao){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.merge(cartao);
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
    
    public static void deletarCartao(Cartao cartao){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.remove(cartao);
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
    
    public static Cartao consultarCartao(Long idCartao){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        Cartao cartaoResultado = null;
        
        try{ 
            et.begin();
            cartaoResultado = EM.find(Cartao.class, idCartao);
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
        
        return cartaoResultado;
    }
    
}
