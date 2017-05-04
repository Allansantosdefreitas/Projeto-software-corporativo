/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.testes;

import com.sistema.model.Cliente;
import com.sistema.model.Endereco;
import com.sistema.model.Pet;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author Usuario
 */
public class EnderecoTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public EnderecoTest() {
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

        em.persist(endereco);
        
        assertNotNull(endereco.getIdEndereco());
        
    }


}
