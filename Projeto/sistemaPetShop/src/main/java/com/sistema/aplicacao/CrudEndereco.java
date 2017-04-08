/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Endereco;
import com.sistema.model.Usuario;
import com.sistema.model.Veterinario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Luis Henrique
 */
public class CrudEndereco {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Endereco endereco = new Endereco("Rua A", 404, "not found", "400-400", "undefined", cliente);
        Endereco endereco = new Endereco();
        Veterinario user = CrudVeterinario.consultarVeterinario(Long.parseLong("1"));
        endereco = preencherEndereco(user);
        
        //inserirEndereco(endereco);
        
        endereco = consultarEndereco(Long.parseLong("5"));
        
        
        endereco.setBairro("AlphaVille");
        endereco.setLogradouro("Bairro");
        
        //atualizarEndereco(endereco);
        
        deletarEndereco(endereco);
        
    }
    
    public static void inserirEndereco(Endereco endereco){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.persist(endereco);
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
    
    public static void atualizarEndereco(Endereco endereco){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            et.begin();
            em.merge(endereco);
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

    public static void deletarEndereco(Endereco endereco){
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();
            
            Endereco removerEndereco = em.merge(endereco);
            
            et.begin();
            em.remove(removerEndereco);
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
 
    public static Endereco consultarEndereco(Long idEndereco){
        EntityManager em = null;
        Endereco enderecoEncontrado = new Endereco();
        try {
            em = EMF.createEntityManager();

            enderecoEncontrado = em.find(Endereco.class, idEndereco);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return enderecoEncontrado;
    }
    
    public static Endereco preencherEndereco(Usuario usuario){
        
        Endereco endereco = new Endereco();
        
        endereco.setBairro("Alto do Mandu");
        endereco.setCep("126486912");
        endereco.setComplemento("Subindo o alto");
        endereco.setLogradouro("Comunidade");
        endereco.setNumero(456);
        endereco.setUsuario(usuario);
        
        return endereco;
    }
}
