package sistema.testes;

import com.sistema.model.Cliente;
import com.sistema.model.Endereco;
import com.sistema.model.Pet;
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
 * @author Jonathan Romualdo
 */
public class PetTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public PetTest() {
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
    public void criaPetValidotest() {
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        Pet pet = new Pet();

        Float peso = 24f;

        pet.setCliente(cliente);
        pet.setNome("Tótó");
        pet.setPedegree(Boolean.TRUE);
        pet.setPeso(peso);
        pet.setRaca("Labrador");

        cliente.setEmail("cliente2@cli.com");
        cliente.setEndereco(endereco);
        cliente.setLogin("cliente_gastador2");
        cliente.setNome("Cliente cli");
        cliente.setSenha("cliente123");

        endereco.setBairro("Bairro");
        endereco.setCep("12763818");
        endereco.setComplemento("Perto dali");
        endereco.setLogradouro("Avenida");
        endereco.setNumero(222);
        endereco.setUsuario(cliente);

        em.persist(pet);
        assertNotNull(pet.getIdPet());

    }

    @Test
    public void criaPetInvalidotest() {

    }

    @Test
    public void atualizaPetValidotest() {

    }

    @Test
    public void atualizaPetInvalidotest() {

    }

}
