package br.com.sistemapetshop.model;

import br.com.sistemapetshop.model.Cliente;
import br.com.sistemapetshop.model.ConsultaMedica;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-10T19:08:01")
@StaticMetamodel(Pet.class)
public class Pet_ { 

    public static volatile SingularAttribute<Pet, Cliente> cliente;
    public static volatile SingularAttribute<Pet, Long> idPet;
    public static volatile SingularAttribute<Pet, Float> peso;
    public static volatile SingularAttribute<Pet, String> raca;
    public static volatile ListAttribute<Pet, ConsultaMedica> ListaConsultaMedica;
    public static volatile SingularAttribute<Pet, String> nome;
    public static volatile SingularAttribute<Pet, Boolean> pedegree;

}