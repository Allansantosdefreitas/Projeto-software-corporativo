package com.sistema.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pet.class)
public abstract class Pet_ {

	public static volatile SingularAttribute<Pet, Cliente> cliente;
	public static volatile SingularAttribute<Pet, Long> idPet;
	public static volatile SingularAttribute<Pet, Float> peso;
	public static volatile SingularAttribute<Pet, String> raca;
	public static volatile ListAttribute<Pet, ConsultaMedica> ListaConsultaMedica;
	public static volatile SingularAttribute<Pet, String> nome;
	public static volatile SingularAttribute<Pet, Boolean> pedegree;

}

