package sistema.testes;

import com.sistema.model.Cliente;
import com.sistema.model.Endereco;
import com.sistema.model.Pet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
    /* FUNCIONA */
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
        em.flush();

        assertNotNull(pet.getIdPet());

    }

    @Test
    /* NÃO FUNCIONA */
    public void criaPetInvalidoTest() {
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        Pet pet = new Pet();

        try {
            Float peso = 24f;

            pet.setCliente(null); //INVÁLIDO
            pet.setNome("Tótó");
            pet.setPedegree(null);
            pet.setPeso(peso);
            pet.setRaca("Labrador");

            cliente.setEmail("cliente2@cli.com");
            cliente.setEndereco(endereco); //Inválido
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
            em.flush();

            assertTrue(false);
        } catch (ConstraintViolationException ex) {
//            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            assertEquals(1, constraintViolations.size());
            assertNull(pet.getIdPet());
        }
    }

    @Test
    /* FUNCIONA */
    public void atualizaPetValidoTest() {
        Query query = em.createNamedQuery("Servico.PorNome", Servico.class);
        query.setParameter("nome", "Entrega de racao%");

        Servico servico = em.find(Servico.class, 3L);

        assertNotNull(servico.getIdServico());

        em.clear();

        servico.setNome("Entrega de racao em domicilio");
        servico.setValor(300.0);

        em.merge(servico);
        em.flush();
        em.clear();

        em.find(Servico.class, servico.getIdServico());

        assertEquals(1, query.getResultList().size());
    }

    @Test
    /* NÃO FUNCIONA */
    public void atualizaPetInvalidoTest() {

    }

    @Test
    /* FUNCIONA */
    public void deletaPetEmTest() {
        Query query = em.createQuery("from Servico s where s.idServico like :id", Servico.class);
        query.setParameter("id", 1);
        Servico servico = (Servico) query.getSingleResult();

        em.remove(servico);
        em.flush();
        //et.commit();

        servico = em.find(Servico.class, servico.getIdServico());

        assertNull(servico);
    }

    @Test
    /* FUNCIONA */
    public void selectJpqlQueryTeste() {
        String sql = "from Servico s where s.valor >= 50";

        TypedQuery<Servico> query = em.createQuery(sql, Servico.class);

        List<Servico> servico = query.getResultList();

        for (int i = 0; i < servico.size(); i++) {
            assertNotNull(servico.get(i).getIdServico());
        }

        assertEquals(2, servico.size());
    }

    @Test
    /* FUNCIONA */
    public void selectJpqlNamedQueryTeste() {
        TypedQuery<Servico> query = em.createNamedQuery("Servico.PorNome", Servico.class);
        query.setParameter("nome", "Banho%");

        List<Servico> listaServicos = query.getResultList();

        for (Servico servico1 : listaServicos) {
            assertNotNull(servico1.getIdServico());
        }

        assertEquals(2, listaServicos.size());
    }

    @Test
    /* NÃO FUNCIONA */
    public void selectNativeQueryTeste() {
        String sql = "select ser.str_nome, ser.dbl_valor from tb_servico ser where ser.dbl_valor >= 40 order by ser.str_nome";
        Query query = em.createNativeQuery(sql, Servico.class);

        List<Servico> listaServicos = (List<Servico>) query.getResultList();

        for (Servico servico : listaServicos) {
            assertNotNull(servico.getIdServico());
        }

        assertEquals(3, listaServicos.size());
    }

    @Test
    /* FUNCIONA */
    public void selectNativeNamedQueryTeste() {
        Query query;
        query = em.createNamedQuery("Servico.PorPreco");
        query.setParameter(1, "90");

        List<Object[]> listaServicos = query.getResultList();

        assertEquals(1, listaServicos.size());
    }

    @Test
    /* NÃO FUNCIONA */
    public void atualizaPetQueryTeste() {

    }

    @Test
    /* NÃO FUNCIONA */
    public void deletaPetQuerytTeste() {
        String sql = "delete from Servico ser where ser.idServico = 3";
        String sql2 = "delete from ConsultaGeral cong where cong.servico = 3";

        Query query = em.createQuery(sql);
        Query query2 = em.createQuery(sql2);

        query2.executeUpdate();
        query.executeUpdate();

        Servico servico = em.find(Servico.class, 3L);

        assertNull(servico);
    }

}
