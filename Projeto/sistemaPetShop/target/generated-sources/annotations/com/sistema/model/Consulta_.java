package com.sistema.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Consulta.class)
public abstract class Consulta_ {

	public static volatile SingularAttribute<Consulta, Date> dataMarcada;
	public static volatile SingularAttribute<Consulta, Long> idConsulta;
	public static volatile SingularAttribute<Consulta, StatusConsulta> status;

}

