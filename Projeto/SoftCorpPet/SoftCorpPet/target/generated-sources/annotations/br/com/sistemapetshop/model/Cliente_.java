package br.com.sistemapetshop.model;

import br.com.sistemapetshop.model.Cartao;
import br.com.sistemapetshop.model.Pet;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-15T05:47:18")
@StaticMetamodel(Cliente.class)
public class Cliente_ extends Usuario_ {

    public static volatile ListAttribute<Cliente, Cartao> cartao;
    public static volatile ListAttribute<Cliente, Pet> listaPet;

}