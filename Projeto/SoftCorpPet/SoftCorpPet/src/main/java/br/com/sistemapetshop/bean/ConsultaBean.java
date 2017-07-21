/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.ConsultaGeral;
import br.com.sistemapetshop.model.StatusConsulta;
import br.com.sistemapetshop.negocio.ConsultaGeralService;
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
 * @author Henrique
 */
@ManagedBean(name = "ConsultaGeralManagedBean")
@SessionScoped
public class ConsultaBean implements Serializable {

    @EJB
    private ConsultaGeralService consultaGeralService;

    private ConsultaGeral consultaGeral;
    private List<ConsultaGeral> listaConsultaGeral;
    private StatusConsulta statusConsulta;

    @PostConstruct
    public void constroi() {
        consultaGeral = new ConsultaGeral();

        listarConsultaGeral();
    }

    public void constroiConsultaGeral() {
        consultaGeral = new ConsultaGeral();
    }

    public void salvar() {

        String defaultErrorMsg = "Ocorreu um erro inesperado";
        String defaultSuccessMsg = "Salvo com sucesso";

        try {
            consultaGeralService.atualizar(consultaGeral);
            Messages.addGlobalInfo(defaultSuccessMsg);

            listarConsultaGeral();
        } catch (Exception exception) {
            Messages.addGlobalError(defaultErrorMsg);
            exception.printStackTrace();
        } finally {
            constroiConsultaGeral();
        }
    }

    public void editar(ActionEvent evento) {
        consultaGeral = (ConsultaGeral) evento.getComponent().getAttributes().get("consultaSelecionada");
    }

    public void excluir(ActionEvent evento) {

        String defaultSuccessMsg = "Consulta removida com sucesso";
        String defaultErrorMsg = "Erro ao excluir a consulta";

        //Qual foi o componente clicado? Qual são os atributos? Qual o nome do atributo que eu quero trabalhar?
        consultaGeral = (ConsultaGeral) evento.getComponent().getAttributes().get("servicoSelecionado");

        try {
            System.out.println("CONSULTA: " + consultaGeral.getServico()+ "DATA: "+ consultaGeral.getDataMarcada());
            consultaGeralService.remover(consultaGeral);

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
            listaConsultaGeral = consultaGeralService.listar();
        } catch (Exception ex) {
            Messages.addGlobalError(errorMsg);
        }
    }
    
    public StatusConsulta[] getStatusConsultas(){
        return StatusConsulta.values();
    }
    
    public StatusConsulta getStatusConsulta(){
        return statusConsulta;
    }
    
    public void setStatusConsulta(StatusConsulta statusConsulta){
        this.statusConsulta = statusConsulta;
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

