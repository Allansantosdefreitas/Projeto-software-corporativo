/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import br.com.sistemapetshop.model.Cliente;
import javax.ejb.Stateless;

/**
 *
 * @author jonathanpereira
 */
@Stateless
public class ClienteRepository extends GenericDao<Cliente> {

    public ClienteRepository() {
        super(Cliente.class);
    }
    
}
