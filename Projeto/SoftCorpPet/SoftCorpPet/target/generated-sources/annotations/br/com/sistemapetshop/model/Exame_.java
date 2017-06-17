package br.com.sistemapetshop.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Exame.class)
public abstract class Exame_ {

	public static volatile SingularAttribute<Exame, String> tipo;
	public static volatile ListAttribute<Exame, ConsultaMedica> listaConsultaMedica;
	public static volatile SingularAttribute<Exame, Double> valor;
	public static volatile SingularAttribute<Exame, String> nome;
	public static volatile SingularAttribute<Exame, Long> idExame;
	public static volatile SingularAttribute<Exame, String> descricao;

}

