package br.com.sistemapetshop.model;

import br.com.sistemapetshop.model.Funcionario;
import br.com.sistemapetshop.model.Servico;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-22T06:01:00")
@StaticMetamodel(ConsultaGeral.class)
public class ConsultaGeral_ extends Consulta_ {

    public static volatile SingularAttribute<ConsultaGeral, Funcionario> funcionario;
    public static volatile SingularAttribute<ConsultaGeral, Servico> servico;

}