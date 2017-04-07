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

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");

    public static void main(String[] args) {

        try {
            Long idCartao = inserirCartao();

            Cartao cartaoEncontrado = consultarCartao(idCartao);
            System.out.println("Bandeira: " + cartaoEncontrado.getBandeira());
            System.out.println("Numero: " + cartaoEncontrado.getNumero());
            System.out.println("Validade: " + cartaoEncontrado.getDataValidade());
            
            atualizarCartao(cartaoEncontrado);
            
            //deletarCartao(cartaoEncontrado);

        } finally {
            EMF.close();
        }
    }

    public static Long inserirCartao() {
        EntityManager em = null;
        EntityTransaction et = null;

        Cartao cartao = preencherCartao();

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.persist(cartao);
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

        return cartao.getIdCartao();

    }

    public static void atualizarCartao(Cartao cartao) {
        EntityManager em = null;
        EntityTransaction et = null;
        
        Cartao cartaoEditado = editaCartao(cartao);

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            System.out.println("Salvando alterações...");
            em.merge(cartaoEditado);
            et.commit();
            System.out.println("Alterações salvas com sucesso!");
            
            System.out.println("Bandeira: " + cartaoEditado.getBandeira());
            System.out.println("Numero: " + cartaoEditado.getNumero());
            System.out.println("Validade: " + cartaoEditado.getDataValidade());
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

    public static void deletarCartao(Cartao cartao) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.remove(cartao);
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

    public static Cartao consultarCartao(Long idCartao) {
        EntityManager em = null;

        Cartao cartaoResultado = null;
        
        try {
            em = EMF.createEntityManager();

            System.out.println("Consultando usuário na base...");
            cartaoResultado = em.find(Cartao.class, idCartao);       

        } finally {
            if (em != null) {
                em.close();
            }
        }
        
        return cartaoResultado;
    }

    public static Cartao preencherCartao() {

        Calendar calendario = Calendar.getInstance();
        calendario.set(2018, 7, 17);

        Cartao cartaoPreenchido = new Cartao("Visa", "1234568790123456", calendario.getTime());

        return cartaoPreenchido;
    }
    
    public static Cartao editaCartao(Cartao cartao){
 
        cartao.setBandeira("Master");
        cartao.setNumero("1234512340121234");
        
        return cartao;
    }

}
