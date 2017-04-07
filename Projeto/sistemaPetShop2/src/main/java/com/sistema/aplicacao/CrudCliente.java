/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.aplicacao;

import com.sistema.model.Cartao;
import com.sistema.model.Cliente;
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

        try {

            Long idCliente = inserirCliente();

            Cliente clienteEncontrado = consultarCliente(idCliente);
            System.out.println("Login: " + clienteEncontrado.getLogin());
            System.out.println("Nome: " + clienteEncontrado.getNome());
            System.out.println("Email: " + clienteEncontrado.getEmail());

            atualizarCliente(clienteEncontrado);

            //deletarCliente(clienteEncontrado);
        } finally {
            EMF.close();
        }

    }

    public static Long inserirCliente() {
        EntityManager em = null;
        EntityTransaction et = null;

        Cliente cliente = preencherCliente();

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

        return cliente.getId();
    }

    public static void atualizarCliente(Cliente cliente) {
        EntityManager em = null;
        EntityTransaction et = null;

        Cliente clienteEditado = editaCliente(cliente);

        try {
            em = EMF.createEntityManager();
            et = em.getTransaction();

            et.begin();
            System.out.println("Salvando alterações...");
            em.merge(clienteEditado);
            et.commit();

            System.out.println("Alterações salvas com sucesso!");

            System.out.println("Nome: " + clienteEditado.getNome());
            System.out.println("Email: " + clienteEditado.getEmail());
            System.out.println("Email: " + clienteEditado.getLogin());
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

            et.begin();
            em.remove(cliente);
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

        Cliente clienteEncontrado = null;

        try {
            em = EMF.createEntityManager();

            clienteEncontrado = em.find(Cliente.class, idCliente);

        } finally {
            if (em != null) {
                em.close();
            }
        }
        return clienteEncontrado;
    }

    public static Cliente preencherCliente() {
        Cliente cliente = new Cliente();

        Calendar calendario = Calendar.getInstance();
        calendario.set(2018, 7, 17);

        Cartao cartao = new Cartao("Visa", "1234568790123456", calendario.getTime());

        List<Cartao> listaCartao = null;
        listaCartao.add(cartao);

        cliente.setNome("Kirk");
        cliente.setCartao(listaCartao);
        cliente.setEmail("kirk@capitao.com");
        cliente.setLogin("kirkCapitao");
        cliente.setSenha("melhorCapitao");

        return cliente;
    }

    public static Cliente editaCliente(Cliente cliente) {

        cliente.setNome("Spock");
        cliente.setEmail("spock@capitaoEnterprise.com");
        cliente.setLogin("MelhorSpockTerra");

        return cliente;
    }
}
