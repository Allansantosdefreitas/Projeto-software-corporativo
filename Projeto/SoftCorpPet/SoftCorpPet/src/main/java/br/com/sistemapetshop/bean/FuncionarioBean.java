/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.EspecialidadeFuncionario;
import br.com.sistemapetshop.model.Funcionario;
import br.com.sistemapetshop.negocio.NegocioFuncionario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "FuncionarioManagedBean")
@SessionScoped
public class FuncionarioBean implements Serializable {

    @EJB
    private NegocioFuncionario negocioFuncionario;

    private Funcionario funcionario;
    private List<Funcionario> listaFuncionarios;

    @PostConstruct
    public void inicializar() {
        funcionario = new Funcionario();

        listar();
    }

    public EspecialidadeFuncionario[] getEspecialidadeFuncionario() {
        return EspecialidadeFuncionario.values();
    }

    public void constroiFuncionario() {
        funcionario = new Funcionario();
    }

    public void salvar() {

        String successMsg = "Salvo com sucesso";
        String errorMsg = " Ocorreu um erro inesperado";

        try {

            negocioFuncionario.atualizarServico(funcionario);

            Messages.addGlobalInfo(successMsg);
            listar();
        } catch (Exception ex) {

            Messages.addGlobalError(errorMsg);
            ex.printStackTrace();
        }
    }

    public void editar(ActionEvent evento) {
        funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

        salvar();
    }

    public void remover(ActionEvent evento) {

        String successMsg = "Removido com sucesso";
        String errorMsg = " Ocorreu um erro inesperado";

        funcionario = (Funcionario) evento.getComponent().getAttributes().get("funcionarioSelecionado");

        try {

        } catch (Exception ex) {

        }

        constroiFuncionario();

    }

    public void listar() {
        listaFuncionarios = negocioFuncionario.listarServicos();
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getListaFuncionarios() {
        return listaFuncionarios;
    }

    public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
        this.listaFuncionarios = listaFuncionarios;
    }
}
