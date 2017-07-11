package br.com.sistemapetshop.model;

import br.com.sistemapetshop.model.ConsultaGeral;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-10T19:08:01")
@StaticMetamodel(Servico.class)
public class Servico_ { 

    public static volatile SingularAttribute<Servico, Long> idServico;
    public static volatile SingularAttribute<Servico, Double> valor;
    public static volatile ListAttribute<Servico, ConsultaGeral> listaConsultaGeral;
    public static volatile SingularAttribute<Servico, String> nome;

}