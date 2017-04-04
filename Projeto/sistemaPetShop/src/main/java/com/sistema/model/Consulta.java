package com.sistema.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
