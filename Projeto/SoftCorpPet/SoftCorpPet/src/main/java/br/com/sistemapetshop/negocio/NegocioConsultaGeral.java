/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.negocio;

import br.com.sistemapetshop.model.ConsultaGeral;
import static br.com.sistemapetshop.model.Consulta_.idConsulta;
import br.com.sistemapetshop.model.Servico;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Henrique
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioConsultaGeral {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirConsultaGeral(ConsultaGeral consultaGeral) {
        em.persist(consultaGeral);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarConsultaGeral(ConsultaGeral consultaGeral) {
        em.merge(consultaGeral);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarConsultaGeral(ConsultaGeral consultaGeral) {
        em.remove(consultaGeral);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ConsultaGeral consultarConsultaGeral(Long idEndereco) {
        return em.find(ConsultaGeral.class, idConsulta);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ConsultaGeral> listarConsultaGeral() {

        return (List<ConsultaGeral>) em.createQuery("From Consulta c").getResultList();
    }
}
