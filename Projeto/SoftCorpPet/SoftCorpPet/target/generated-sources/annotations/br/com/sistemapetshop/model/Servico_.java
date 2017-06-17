package br.com.sistemapetshop.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servico.class)
public abstract class Servico_ {

	public static volatile SingularAttribute<Servico, Long> idServico;
	public static volatile SingularAttribute<Servico, Double> valor;
	public static volatile ListAttribute<Servico, ConsultaGeral> listaConsultaGeral;
	public static volatile SingularAttribute<Servico, String> nome;

}

