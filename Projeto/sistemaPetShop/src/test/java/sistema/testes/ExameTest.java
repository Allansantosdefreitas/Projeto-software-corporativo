/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.testes;

import com.sistema.model.Exame;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void criaExameValidoTeste() {
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
    
    /*
    @Test
    public void criaExameInvalidoTeste() {
        Exame exameInvalido = new Exame();
        double valor = 70;
        
        exameInvalido.setNome("Dentario");
        exameInvalido.setDescricao("Examina a arcada dentaria");
        exameInvalido.setTipo("Dental");
        exameInvalido.setValor(null);

        em.persist(exameInvalido);
        et.commit();
        
        assertNull(exameInvalido.getIdExame());
        
    }
    */
    
    /*
    @Test
    public void atualizaExameValidoTeste() {
        Exame exameAtt = new Exame();
        
        exameAtt.setNome("Oral");
        exameAtt.setDescricao("Examina a arcada dentaria");
        exameAtt.setTipo("Dental");
        exameAtt.setValor(new Double(90));
        
        em.merge(exameAtt);
        et.commit();
        
        exameAtt = em.find(Exame.class, exameAtt.getIdExame());
        
        assertEquals("Oral", exameAtt.getNome());
    }
    */

    /*@Test
    public void atualizaExameInvalidoTeste() {
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
    public void deletaExameTeste(){
        Query query = em.createQuery("from Exame e where e.nome like :nome ", Exame.class);
        query.setParameter("nome", "Cardiovascular");
        Exame exame = (Exame) query.getSingleResult();

        em.remove(exame);
        et.commit();

        exame = em.find(Exame.class, exame.getIdExame());

        assertNull(exame);
        
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
        TypedQuery<Exame> query = em.createNamedQuery("Exame.PorTipo", Exame.class);
        query.setParameter(1,"Cirurgico");
        
        List<Exame> listaExames = query.getResultList();

        assertEquals(1, listaExames.size());
        
        assertTrue(true);
    }

}
