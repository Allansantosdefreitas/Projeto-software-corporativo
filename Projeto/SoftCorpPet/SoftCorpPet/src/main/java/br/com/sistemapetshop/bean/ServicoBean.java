package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Servico;
import br.com.sistemapetshop.repository.NegocioServico;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "ServicoManagedBean")
@SessionScoped
public class ServicoBean {

    @EJB
    private NegocioServico negocioServico;

    private Servico servico;

    @PostConstruct
    public void constroi() {
        servico = new Servico();
    }

    public List<Servico> listarServicos() {
        return negocioServico.listarServicos();
    }

    public void adicionar() {
        negocioServico.inserirServico(servico);
    }

    public Servico getServico() {
        return servico;
    }

}
