package com.sistema.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lon_id", nullable = false)
    private Long id;

    @Column(name = "str_nome", length = 60, nullable = false)
    private String nome;

    @Column(name = "str_email", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "str_login", length = 60, nullable = false, unique = true)
    private String login;

    @Column(name = "str_senha", length = 16, nullable = false)
    private String senha;

    @OneToOne(fetch = FetchType.LAZY, optional = false, orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "ext_IdEndereco", referencedColumnName = "lon_id")
    private Endereco endereco;

    public Usuario() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
