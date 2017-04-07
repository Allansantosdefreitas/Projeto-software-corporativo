/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sistema.aplicacao;

import com.sistema.model.Veterinario;
import com.sistema.model.Consulta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Luis Henrique
 */
public class CrudVeterinario {

    private static EntityManagerFactory EMF = null;
    private static EntityManager EM = null;
    private static EntityTransaction et = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Veterinario vet = new Veterinario();
        
        List<Consulta> listaConsultas = null;
        
        //terminar inserir
        
        //consultar
        
        //atualizar
        
        //deletar
        
        //consultar
    }
    
    public static void inserirVeterinario(Veterinario veterinario){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction();
        
        try{ 
            et.begin();
            EM.persist(veterinario);
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
    
    public static void atualizarVeterinario(Veterinario veterinario){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.merge(veterinario);
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

    public static void deletarVeterinario(Veterinario veterinario){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.remove(veterinario);
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
 
    public static void consultarVeterinario(Long idVeterinario){
        EMF = Persistence.createEntityManagerFactory("sistemapetshopPU");
        EM = EMF.createEntityManager();
        et  = EM.getTransaction(); 
        
        try{ 
            et.begin();
            EM.find(Veterinario.class, idVeterinario);
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
