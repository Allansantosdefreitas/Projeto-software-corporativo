/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Grupo;
import br.com.sistemapetshop.negocio.GrupoRepository;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author allanfreitas
 */
@ManagedBean(name = "grupoBean")
@ViewScoped
public class GrupoBean implements Serializable{

    @EJB
    private GrupoRepository grupoRepository;

    public GrupoBean() {

    }

    public Grupo getGrupo(String nomeGrupo) {
        return grupoRepository.getGrupo(new String[]{nomeGrupo});
    }

}
