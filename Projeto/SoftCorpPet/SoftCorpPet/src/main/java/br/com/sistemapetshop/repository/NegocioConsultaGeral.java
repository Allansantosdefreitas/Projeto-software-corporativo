/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sistemapetshop.repository;

import br.com.sistemapetshop.model.ConsultaGeral;
import static br.com.sistemapetshop.model.Consulta_.idConsulta;
import javax.ejb.Stateless;
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
 * @author LuisHenrique
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NegocioConsultaGeral {

    @PersistenceContext(unitName = "corporativoPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void inserirConsultaGeral(ConsultaGeral consulta) {
        em.persist(consulta);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void atualizarConsultaGeral(ConsultaGeral consulta) {
        em.merge(consulta);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void deletarConsultaGeral(ConsultaGeral consulta) {
        em.remove(consulta);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public ConsultaGeral consultarServico(Long idServico) {
        return em.find(ConsultaGeral.class, idConsulta);
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<ConsultaGeral> listarConsultasGerais() {

        return (List<ConsultaGeral>) em.createQuery("From Consulta c").getResultList();
    }
}
