package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Grupo;
import br.com.sistemapetshop.model.Veterinario;
import br.com.sistemapetshop.negocio.NegocioGrupoBean;
import br.com.sistemapetshop.negocio.NegocioVeterinario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.omnifaces.util.Messages;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "veterinarioManagedBean")
@SessionScoped
public class VeterinarioBean {

    @EJB
    private NegocioVeterinario negocioVeterinario;
    
    @EJB
    private NegocioGrupoBean grupoBean;
    private Veterinario veterinario;

    @PostConstruct
    public void constroi() {
        veterinario = new Veterinario();
    }
    
    public void salvar(){
        try{
            veterinario.setGrupo(grupoBean.getGrupo(Grupo.GRUPO_POR_NOME, new String[]{veterinario.VETERINARIO}));
            negocioVeterinario.atualizarVeterinario(veterinario);
            
            Messages.addGlobalInfo("cadastrado com sucesso!");
        }catch(Exception ex){
            
            Messages.addGlobalError("Ocorreu algum erro.");
            ex.printStackTrace(); // show error :) :(
        }
          
    }

    public void remover(){
        
    }
    
    public void atualizar(){
        
    }
    
    public void editar(){
        
    }
    
    public List<Veterinario> listarVeterinarios() {
        return negocioVeterinario.listarVeterinario();
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

}
