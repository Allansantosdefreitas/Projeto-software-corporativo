package br.com.sistemapetshop.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Cartao.class)
public abstract class Cartao_ {

	public static volatile SingularAttribute<Cartao, Cliente> cliente;
	public static volatile SingularAttribute<Cartao, Long> idCartao;
	public static volatile SingularAttribute<Cartao, String> numero;
	public static volatile SingularAttribute<Cartao, Date> dataValidade;
	public static volatile SingularAttribute<Cartao, String> bandeira;

}

