/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.ConsultaGeral;
import br.com.sistemapetshop.repository.NegocioConsultaGeral;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
/**
 *
 * @author LuisHenrique
 */
@ManagedBean(name = "ConsultaGeralManagedBean")
@SessionScoped
public class ConsultaGeralBean {

    /**
     * Creates a new instance of ConsultaGeralBean
     */
    
    @EJB
    private NegocioConsultaGeral negocioConsultaGeral;
    
    private ConsultaGeral consultaGeral;

    @PostConstruct
    public void constroi() {
        consultaGeral = new ConsultaGeral();
    }

    public List<ConsultaGeral> listarConsultas() {
        return negocioConsultaGeral.listarConsultasGerais();
    }

    public void adicionar() {
        negocioConsultaGeral.inserirConsultaGeral(consultaGeral);
    }

    public ConsultaGeral getConsultaGeral() {
        return consultaGeral;
    }
    
}
