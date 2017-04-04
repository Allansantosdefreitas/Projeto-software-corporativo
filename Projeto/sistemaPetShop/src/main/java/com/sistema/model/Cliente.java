/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jonathan Romualdo
 */
public class Cliente extends Usuario implements Serializable {

    private Cartao cartao;
    private List<Pet> listaPet;

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public List<Pet> getListaPet() {
        return listaPet;
    }

    public void setListaPet(List<Pet> listaPet) {
        this.listaPet = listaPet;
    }

}
