/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Cartao;
import br.com.sistemapetshop.negocio.ClienteRepository;
import br.com.sistemapetshop.model.Cliente;
import br.com.sistemapetshop.model.Endereco;
import br.com.sistemapetshop.model.Pet;
import br.com.sistemapetshop.util.WebServiceCep;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author jonathanpereira
 */
@ManagedBean (name = "clienteManagedBean")
@SessionScoped
public class ClienteBean {

    @EJB
    private ClienteRepository clienteRepository;

    private List<Cliente> listaClientes;
    private List<Cartao> listaCartoes;
    private List<Pet> listaPets;
    private Endereco endereco;
    private Cliente cliente;

    public void inicializar() {
        cliente = new Cliente();

        listar();
    }

    public void salvar() {

        try {
            
            //Atribuir o endereço ao cliente antesde persistir
            cliente.setEndereco(endereco);

            clienteRepository.salvar(cliente);

            Messages.addGlobalInfo("Salvo com sucesso.");
        } catch (Exception ex) {

            Messages.addGlobalError("Ocorreu um erro inesperado.");
            ex.printStackTrace();
        }
    }

    public void atualizar() {

        try {

            clienteRepository.atualizar(cliente);

            Messages.addGlobalInfo("Atualizado com sucesso.");
        } catch (Exception ex) {

            Messages.addGlobalError("Ocorreu um erro inesperado.");
            ex.printStackTrace();
        }
    }

    public void remover() {

        try {

            clienteRepository.remover(cliente);

            Messages.addGlobalInfo("Cliente removido com sucesso!");
        } catch (Exception ex) {

            Messages.addGlobalError("Ocorreu um erro inesperado.");
            ex.printStackTrace();
        }
    }

    public void listar() {

        try {

            listaClientes = clienteRepository.listar();
        } catch (Exception ex) {

            Messages.addGlobalError("Ocorreu um erro inesperado ao carregar a lista.");
            ex.printStackTrace();
        }
    }

    public void buscaCep() {
        
        WebServiceCep webServiceCep = WebServiceCep.searchCep(endereco.getCep());

        if (webServiceCep.wasSuccessful()) {

            endereco.setLogradouro(webServiceCep.getLogradouroFull());
            endereco.setBairro(webServiceCep.getBairro());

            Messages.addGlobalInfo("Cep encontrado!");
        } else {

            Messages.addGlobalError("Cep não encontrado");
        }
    }

    public List<Cartao> getListaCartoes() {
        return listaCartoes;
    }

    public void setListaCartoes(List<Cartao> listaCartoes) {
        this.listaCartoes = listaCartoes;
    }

    public List<Pet> getListaPets() {
        return listaPets;
    }

    public void setListaPets(List<Pet> listaPets) {
        this.listaPets = listaPets;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
