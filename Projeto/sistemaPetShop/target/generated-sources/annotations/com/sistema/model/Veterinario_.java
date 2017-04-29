package com.sistema.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Veterinario.class)
public abstract class Veterinario_ extends com.sistema.model.Usuario_ {

	public static volatile SingularAttribute<Veterinario, String> especialidade;
	public static volatile SingularAttribute<Veterinario, String> crmv;
	public static volatile ListAttribute<Veterinario, ConsultaMedica> ListaConsultaMedica;

}

