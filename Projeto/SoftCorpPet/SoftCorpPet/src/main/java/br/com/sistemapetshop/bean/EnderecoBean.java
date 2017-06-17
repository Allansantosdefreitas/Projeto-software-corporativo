/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Endereco;
import br.com.sistemapetshop.model.Veterinario;
import br.com.sistemapetshop.repository.NegocioEndereco;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "EnderecoManagedBean")
@SessionScoped
public class EnderecoBean {

    @EJB
    private NegocioEndereco negocioEndereco;

    private Endereco endereco;

    @PostConstruct
    public void constroi() {
        endereco = new Endereco();
    }

    public List<Endereco> listarEnderecos() {
        return negocioEndereco.listarEnderecos();
    }

    public void adicionar() {
        negocioEndereco.inserirEndereco(endereco);
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
