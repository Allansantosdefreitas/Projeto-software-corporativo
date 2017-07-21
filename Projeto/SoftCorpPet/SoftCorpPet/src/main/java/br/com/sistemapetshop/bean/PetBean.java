/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.bean;

import br.com.sistemapetshop.model.Pet;
import br.com.sistemapetshop.negocio.PetService;
import javax.ejb.Stateful;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;

/**
 *
 * @author Henrique
 */
@Stateful
@ManagedBean(name = "PetManagedBean")
public class PetBean implements Serializable{

    @EJB
    private PetService petService;

    private Pet pet;
    private List<Pet> listapet;

    @PostConstruct
    public void constroi() {
        pet = new Pet();

        listarpet();
    }

    public void constroipet() {
        pet = new Pet();
    }

    public void salvar() {

        String defaultErrorMsg = "Ocorreu um erro inesperado";
        String defaultSuccessMsg = "Salvo com sucesso";

        try {
            petService.atualizar(pet);
            Messages.addGlobalInfo(defaultSuccessMsg);

            listarpet();
        } catch (Exception exception) {
            Messages.addGlobalError(defaultErrorMsg);
            exception.printStackTrace();
        } finally {
            constroipet();
        }
    }

    public void editar(ActionEvent evento) {
        pet = (Pet) evento.getComponent().getAttributes().get("consultaSelecionada");
    }

    public void excluir(ActionEvent evento) {

        String defaultSuccessMsg = "Consulta removida com sucesso";
        String defaultErrorMsg = "Erro ao excluir a consulta";

        //Qual foi o componente clicado? Qual são os atributos? Qual o nome do atributo que eu quero trabalhar?
        pet = (Pet) evento.getComponent().getAttributes().get("servicoSelecionado");

        try {
            System.out.println("NOME DO ANIMAL: " + pet.getNome()+ "RAÇA: "+ pet.getRaca());
            petService.remover(pet);

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

    public void listarpet() {

        String errorMsg = "Erro ao carregar a lista";

        try {
            listapet = petService.listar();
        } catch (Exception ex) {
            Messages.addGlobalError(errorMsg);
        }
    }
    
        public List<Pet> getListapet() {
        return listapet;
    }

    public void setListapet(List<Pet> listapet) {
        this.listapet = listapet;
    }

    public Pet getpet() {
        return pet;
    }

}