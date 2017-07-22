/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import static br.com.sistemapetshop.acesso.Papel.ADMINISTRADOR;
import static br.com.sistemapetshop.acesso.Papel.CLIENTE;
import static br.com.sistemapetshop.acesso.Papel.FUNCIONARIO;
import br.com.sistemapetshop.model.Pet;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

/**
 *
 * @author jonathanpereira
 */
@Stateless
@DeclareRoles({ADMINISTRADOR, FUNCIONARIO, CLIENTE})
public class PetService  extends Service<Pet>{
    
    public PetService(){
        super(Pet.class);
    }
//    
//    @RolesAllowed({CLIENTE})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void salvar(Pet pet) {
        EntityManager em = getEntityManager();
        
        if(!checarExistencia(Pet.POR_NOME, pet.getNome())){
            em.persist(pet);
        }
    }
//
//    @RolesAllowed({CLIENTE})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void atualizar(Pet pet) {
        EntityManager em = getEntityManager();
        em.merge(pet);
    }
//
//    @RolesAllowed({CLIENTE})
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void remover(Pet pet) {
        EntityManager em = getEntityManager();
        em.remove(em.merge(pet));
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public List<Pet> listar(){
        return getEntidades(Pet.TODOS);
    }
}
