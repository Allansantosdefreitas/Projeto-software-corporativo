/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.ConsultaGeral;
import br.com.sistemapetshop.model.Servico;
import br.com.sistemapetshop.negocio.NegocioConsultaGeral;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Henrique
 */
@ManagedBean(name = "ConsultaGeralManagedBean")
@SessionScoped
public class ConsultaBean {

    @EJB
    private NegocioConsultaGeral negocioConsultaGeral;

    private ConsultaGeral consultaGeral;
    private List<ConsultaGeral> listaConsultaGeral;

    @PostConstruct
    public void constroi() {
        consultaGeral = new ConsultaGeral();

        listarConsultaGeral();
    }

    public void constroiServico() {
        consultaGeral = new ConsultaGeral();
    }

    public void salvar() {

        String defaultErrorMsg = "Ocorreu um erro inesperado";
        String defaultSuccessMsg = "Salvo com sucesso";

        try {
            negocioConsultaGeral.atualizarConsultaGeral(consultaGeral);
            Messages.addGlobalInfo(defaultSuccessMsg);

            listarConsultaGeral();
        } catch (Exception exception) {
            Messages.addGlobalError(defaultErrorMsg);
            exception.printStackTrace();
        } finally {
            constroiServico();
        }
    }

    public void editar(ActionEvent evento) {
        consultaGeral = (ConsultaGeral) evento.getComponent().getAttributes().get("servicoSelecionado");
    }

    public void excluir(ActionEvent evento) {

        String defaultSuccessMsg = "Consulta removida com sucesso";
        String defaultErrorMsg = "Erro ao excluir a consulta";

        //Qual foi o componente clicado? Qual são os atributos? Qual o nome do atributo que eu quero trabalhar?
        consultaGeral = (ConsultaGeral) evento.getComponent().getAttributes().get("servicoSelecionado");

        try {
            System.out.println("CONSULTA: " + consultaGeral.getServico()+ "DATA: "+ consultaGeral.getDataMarcada());
            negocioConsultaGeral.deletarConsultaGeral(consultaGeral);

            Messages.addGlobalInfo(defaultSuccessMsg);
            constroi();

        } catch (Exception erro) {
            Messages.addGlobalError(defaultErrorMsg);
            erro.printStackTrace();
        }
//        } finally {
//            constroiServico();
//        }
    }

    public void listarConsultaGeral() {

        String errorMsg = "Erro ao carregar a lista";

        try {
            listaConsultaGeral = negocioConsultaGeral.listarConsultaGeral();
        } catch (Exception ex) {
            Messages.addGlobalError(errorMsg);
        }
    }

    public List<ConsultaGeral> getListaConsultaGeral() {
        return listaConsultaGeral;
    }

    public void setListaConsultaGeral(List<ConsultaGeral> listaConsultaGeral) {
        this.listaConsultaGeral = listaConsultaGeral;
    }

    public ConsultaGeral getConsultaGeral() {
        return consultaGeral;
    }

}

