/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Cartao;
import com.sistema.model.Cliente;
import com.sistema.model.Endereco;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Jonathan Romualdo
 */
public class CrudCliente {

    private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Calendar calendario = Calendar.getInstance();
        calendario.set(2018, 7, 17);

        // Inserir ------------------------------------------------- OK
        // Cartao
        Cartao cartao = new Cartao("Visa", "1999-2001-2003", calendario.getTime());
        // ListaCartao
        List<Cartao> listaCartao = new ArrayList<Cartao>();
        listaCartao.add(cartao);

        // Endereco
        Endereco endereco = new Endereco("Rua A", 404, "próximo à Evil Corp", "400-400", "Várzea", cliente);

        cliente.setNome("James T. Kirk");
        cliente.setCartao(listaCartao);
        cliente.setEmail("kirk@capitao.com");
        cliente.setLogin("kirkCapitao");
        cliente.setSenha("melhorCapitao");
        cliente.setEndereco(endereco);

        //inserirCliente(cliente);
        Cliente clienteResultado = new Cliente();
        clienteResultado = consultarCliente(Long.parseLong("10"));

        System.out.println("Nome: " + clienteResultado.getNome());
        System.out.println("Email: " + clienteResultado.getEmail());

//       
        cliente.setNome("Spock");
        cliente.setEmail("spock@capitaoEnterprise.com");

        atualizarCliente(cliente);

        deletarCliente(cliente);

    }

    public static void inserirCliente(Cliente cliente) {
        /* :) */
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.persist(cliente);
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

    public static void atualizarCliente(Cliente cliente) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            em.merge(cliente);
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

    public static void deletarCliente(Cliente cliente) {
        EntityManager em = null;
        EntityTransaction et = null;

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            Cliente clienteRemove = em.merge(cliente);

            et.begin();
            em.remove(clienteRemove);
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

    public static Cliente consultarCliente(Long idCliente) {
        EntityManager em = null;

        Cliente clienteResultado = null;
        try {
            em = EMF.createEntityManager();

            clienteResultado = em.find(Cliente.class, idCliente);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return clienteResultado;
    }

}
