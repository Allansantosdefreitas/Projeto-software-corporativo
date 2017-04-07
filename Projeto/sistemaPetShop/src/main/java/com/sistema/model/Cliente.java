/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Jonathan Romualdo
 */
public class Cliente extends Usuario implements Serializable {

    @OneToMany (mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn (name = "fk_cartao", referencedColumnName = "id_cartao", nullable = true)
    private List<Cartao> cartao;
    
    
    @OneToMany (mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn (name = "fk_pet", referencedColumnName = "id_pet")
    private List<Pet> listaPet;

    public List<Cartao> getCartao() {
        return cartao;
    }

    public void setCartao(List<Cartao> cartao) {
        this.cartao = cartao;
    }

    public List<Pet> getListaPet() {
        return listaPet;
    }

    public void setListaPet(List<Pet> listaPet) {
        this.listaPet = listaPet;
    }

}
