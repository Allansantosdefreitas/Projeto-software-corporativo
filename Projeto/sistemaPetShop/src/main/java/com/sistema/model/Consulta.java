package com.sistema.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Jonathan Romualdo
 */
@Entity
@Table(name = "tb_consulta")
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lon_id", nullable = false, unique = true)
    private Long idConsulta;

    @Column(name = "dat_dataMarcada")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataMarcada;

    @Column(name = "str_diagnostico", length = 100)
    private String diagnostico;

    @Column(name = "str_status", length = 60)
    private StatusConsulta status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ext_IdPet", referencedColumnName = "lon_id")
    private Pet pet;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn (name = "ext_IdVeterinario", referencedColumnName = "lon_id")
    private Veterinario veterinario;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn (name = "ext_IdConsulta", referencedColumnName = "lon_id")
    private Consulta consulta;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn (name = "ext_IdExame", referencedColumnName = "lon_id")
    private Exame exame;

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getDataMarcada() {
        return dataMarcada;
    }

    public void setDataMarcada(Date dataMarcada) {
        this.dataMarcada = dataMarcada;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public StatusConsulta getStatus() {
        return status;
    }

    public void setStatus(StatusConsulta status) {
        this.status = status;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
    
    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
    
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
    
    public Exame getExame() {
        return exame;
    }

    public void setExame(Exame exame) {
        this.exame = exame;
    }
    
}
