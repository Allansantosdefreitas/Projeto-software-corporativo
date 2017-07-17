/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import static br.com.sistemapetshop.acesso.Papel.ADMINISTRADOR;
import static br.com.sistemapetshop.acesso.Papel.CLIENTE;
import static br.com.sistemapetshop.acesso.Papel.FUNCIONARIO;
import br.com.sistemapetshop.model.Exame;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Stateless;

/**
 *
 * @author jonathanpereira
 */
@Stateless
@DeclareRoles({ADMINISTRADOR, FUNCIONARIO, CLIENTE})
public class ExameService extends Service<Exame> {
    
    public ExameService(){
        super(Exame.class);
    }
}