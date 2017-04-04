/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Cartao;
import com.sistema.model.Cliente;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan Romualdo
 */
public class CrudCliente {
    
    private static EntityManagerFactory EMF = null;
    private static EntityManager EM = null;
    private static EntityTransaction et = null;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        Cliente cliente = new Cliente();
        Calendar calendario = Calendar.getInstance();
        calendario.set(2018, 7, 17);
        
        Cartao cartao = new Cartao("Visa", "1234568790123456", calendario.getTime());
               
        cliente.setNome("Kirk");
        cliente.setCartao(cartao);
        cliente.setEmail("kirk@capitao.com");
        cliente.setLogin("kirkCapitao");
        cliente.setSenha("melhorCapitao");
        
        inserirCliente(cliente);
        
        consultarCliente(Long.valueOf('1'));
        
        cliente.setNome("Spock");
        cliente.setEmail("spock@capitaoEnterprise.com");
        
        atualizarCliente(cliente);
        
        //deletarCliente(cliente);
        
    }
    
    public static void inserirCliente(Cliente cliente){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.persist(cliente);
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
    
    public static void atualizarCliente(Cliente cliente){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.merge(cliente);
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

    public static void deletarCliente(Cliente cliente){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.remove(cliente);
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
 
    public static void consultarCliente(Long idCliente){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.find(Cliente.class, idCliente);
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
