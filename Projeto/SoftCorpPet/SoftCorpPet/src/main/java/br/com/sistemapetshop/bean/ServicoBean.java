package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Servico;
import br.com.sistemapetshop.negocio.NegocioServico;
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
 * @author Jonathan Romualdo
 */
@ManagedBean(name = "ServicoManagedBean")
@SessionScoped
public class ServicoBean implements Serializable {

    @EJB
    private NegocioServico negocioServico;

    private Servico servico;
    private List<Servico> listaServicos;

    @PostConstruct
    public void constroi() {
        servico = new Servico();

        listarServicos();
    }

    public void constroiServico() {
        servico = new Servico();
    }

    public void salvar() {

        String defaultErrorMsg = "Ocorreu um erro inesperado";
        String defaultSuccessMsg = "Salvo com sucesso";

        try {
            negocioServico.atualizarServico(servico);
            Messages.addGlobalInfo(defaultSuccessMsg);

            listarServicos();
        } catch (Exception exception) {
            Messages.addGlobalError(defaultErrorMsg);
            exception.printStackTrace();
        } finally {
            constroiServico();
        }
    }

    public void editar(ActionEvent evento) {
        servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");

        salvar();
    }

    public void excluir(ActionEvent evento) {

        String defaultSuccessMsg = "Serviço removido com sucesso";
        String defaultErrorMsg = "Erro ao carregar a lista";

        //Qual foi o componente clicado? Qual são os atributos? Qual o nome do atributo que eu quero trabalhar?
        servico = (Servico) evento.getComponent().getAttributes().get("servicoSelecionado");

        try {
            negocioServico.deletarServico(servico);

            Messages.addGlobalInfo(defaultSuccessMsg);

            listarServicos();
        } catch (Exception erro) {
            Messages.addGlobalError(defaultErrorMsg);
            erro.printStackTrace();
        } finally {
            constroiServico();
        }
    }

    public void listarServicos() {

        String errorMsg = "Erro ao carregar a lista";
        
        try {
            listaServicos = negocioServico.listarServicos();
        } catch(Exception ex){
            Messages.addGlobalError(errorMsg);
        }
    }

    public List<Servico> getListaServicos() {
        return listaServicos;
    }

    public void setListaServicos(List<Servico> listaServicos) {
        this.listaServicos = listaServicos;
    }

    public Servico getServico() {
        return servico;
    }

}
