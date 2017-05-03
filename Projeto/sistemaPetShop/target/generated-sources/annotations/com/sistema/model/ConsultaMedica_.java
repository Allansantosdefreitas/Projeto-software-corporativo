package com.sistema.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ConsultaMedica.class)
public abstract class ConsultaMedica_ extends com.sistema.model.Consulta_ {

	public static volatile SingularAttribute<ConsultaMedica, Exame> exame;
	public static volatile SingularAttribute<ConsultaMedica, Veterinario> veterinario;
	public static volatile SingularAttribute<ConsultaMedica, String> diagnostico;
	public static volatile SingularAttribute<ConsultaMedica, Pet> pet;

}

