package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Usuario;
import br.com.sistemapetshop.model.Veterinario;
import br.com.sistemapetshop.negocio.NegocioVeterinario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "VeterinarioManagedBean")
@SessionScoped
public class VeterinarioBean {

    @EJB
    private NegocioVeterinario negocioVeterinario;

    private Veterinario veterinario;

    @PostConstruct
    public void constroi() {
        veterinario = new Veterinario();
    }

    public List<Veterinario> listarVeterinarios() {
        return negocioVeterinario.listarVeterinario();
    }

    public void adicionar() {
        negocioVeterinario.inserirVeterinario(veterinario);
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

}
