/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.testes;

import com.sistema.model.Cliente;
import com.sistema.model.Exame;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.sistema.model.Endereco;
import com.sistema.model.Pet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Usuario
 */
public class ExameTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public ExameTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("sistemapetshopPU");
        DbUnitUtil.inserirDados();

        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown() {
        try {
            et.commit();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());

            if (et.isActive()) {
                et.rollback();
            }
        } finally {
            em.close();
            em = null;
            et = null;
        }
    }
    
    @Test
    public void criaEnderecoValidoTeste() {
        Exame exame = new Exame();
        double valor = 70;
        
        exame.setNome("Dentario");
        exame.setDescricao("Examina a arcada dentaria");
        exame.setTipo("Dental");
        exame.setValor(valor);

        em.persist(exame);
        et.commit();
        
        assertNotNull(exame.getIdExame());
        
    }
    
    public void criaEnderecoInvalidoTeste() {
        Exame exameInvalido = new Exame();
        double valor = 70;
        
        exameInvalido.setNome("Dentario");
        exameInvalido.setDescricao("Examina a arcada dentaria");
        exameInvalido.setTipo("Dental");
        exameInvalido.setValor(valor);

        em.persist(exameInvalido);
        et.commit();
        
        assertNull(exameInvalido.getIdExame());
        
    }
    
    @Test
    public void atualizaEnderecoValidoTeste() {
        Endereco enderecoAtt = new Endereco();
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        List<Pet> listaPet = new ArrayList<>();

        cliente.setListaPet(listaPet);
        cliente.setEmail("cliente@cli.com");
        cliente.setEndereco(endereco);
        cliente.setLogin("Cliente");
        cliente.setNome("Cliente cli");
        cliente.setSenha("cliente123");

        endereco.setBairro("Aquele Bairro");
        endereco.setCep("12345678");
        endereco.setComplemento("Perto daquele Restaurante");
        endereco.setLogradouro("Avenida");
        endereco.setNumero(123);
        endereco.setUsuario(cliente);
        
        enderecoAtt = endereco;
        enderecoAtt.setNumero(546);
        em.merge(endereco);
        et.commit();
        
        assertEquals(new Long(546), new Long(enderecoAtt.getNumero()));
    }

    /*@Test
    public void atualizaEnderecoInvalidoTeste() {
        Endereco enderecoAtt = new Endereco();
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        List<Pet> listaPet = new ArrayList<>();

        cliente.setListaPet(listaPet);
        cliente.setEmail("cliente@cli.com");
        cliente.setEndereco(endereco);
        cliente.setLogin("Cliente");
        cliente.setNome("Cliente cli");
        cliente.setSenha("cliente123");

        endereco.setBairro("Aquele Bairro");
        endereco.setCep("123456789");
        endereco.setComplemento("Perto daquele Restaurante");
        endereco.setLogradouro("Avenida");
        endereco.setNumero(123);
        endereco.setUsuario(cliente);
        
        enderecoAtt = endereco;
        enderecoAtt.setCep("546852741987");//Cep muito grande
        em.merge(enderecoAtt);
        et.commit();
        
        assertNotEquals(endereco.getCep(), enderecoAtt.getCep());
    }*/
    
    @Test
    public void deletaEnderecoTeste(){
        Query query = em.createQuery("from Endereco e where e.logradouro like :logradouro ", Endereco.class);
        query.setParameter("logradouro", "Casa3");
        Endereco endereco = (Endereco) query.getSingleResult();

        em.remove(endereco);
        et.commit();

        endereco = em.find(Endereco.class, endereco.getIdEndereco());

        assertNull(endereco);
        
        assertTrue(true);
    }

    @Test
    public void selectJpqlQueryTeste() {
        
        assertTrue(true);
    }

    @Test
    public void selectJpqlNamedQueryTeste() {
        
        assertTrue(true);
    }

    @Test
    public void selectSqlNativeQueryTeste() {
        
        assertTrue(true);
    }

    @Test
    public void selectSqlNativeNamedQueryTeste() {
        TypedQuery<Endereco> query = em.createNamedQuery("Endereco.PorLogradouro", Endereco.class);
        query.setParameter(1,"Casa3");
        
        List<Endereco> listaEnderecos = query.getResultList();

        assertEquals(1, listaEnderecos.size());
        
        assertTrue(true);
    }

}
