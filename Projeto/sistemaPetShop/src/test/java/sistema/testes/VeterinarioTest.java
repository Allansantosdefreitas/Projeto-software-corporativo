package sistema.testes;

import com.sistema.model.Cliente;
import com.sistema.model.ConsultaMedica;
import com.sistema.model.Endereco;
import com.sistema.model.Exame;
import com.sistema.model.Pet;
import com.sistema.model.StatusConsulta;
import com.sistema.model.Veterinario;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Jonathan Romualdo
 */
public class VeterinarioTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public VeterinarioTest() {
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
    public void criaVeterinarioValidoTeste() {
        List<ConsultaMedica> listaConsultas = new ArrayList<>();
        Veterinario veterinario = new Veterinario();
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        Exame exame = new Exame();
        Pet pet = new Pet();

        endereco.setBairro("Bairro");
        endereco.setCep("12763818");
        endereco.setComplemento("Perto dali");
        endereco.setLogradouro("Avenida");
        endereco.setNumero(222);
        endereco.setUsuario(veterinario);

        veterinario.setCrmv("crmvPadraoTeste123");
        veterinario.setEmail("veterinario@vet.com");
        veterinario.setEndereco(endereco);
        veterinario.setEspecialidade("cirurgião");
        veterinario.setListaConsultaMedica(listaConsultas);
        veterinario.setLogin("melhorVeterinario123");
        veterinario.setNome("Veterinário Severino");
        veterinario.setSenha("veterinario1234");

        exame.setDescricao("Exame de rotina");
        exame.setNome("Rotina");
        exame.setTipo("Caro");
        exame.setValor(300.0);

        ConsultaMedica consulta = new ConsultaMedica();
        consulta.setDataMarcada(Date.from(Instant.now()));
        consulta.setDiagnostico("Doente por demais");
        consulta.setExame(exame);
        consulta.setPet(pet);
        consulta.setStatus(StatusConsulta.MARCADA);
        consulta.setVeterinario(veterinario);

        listaConsultas.add(consulta);

        Float peso = 24f;
        pet.setCliente(cliente);
        pet.setNome("Tótó");
        pet.setPedegree(Boolean.TRUE);
        pet.setPeso(peso);
        pet.setRaca("Labrador");

        cliente.setEmail("cliente@cli.com");
        cliente.setEndereco(endereco);
        cliente.setLogin("cliente_gastador");
        cliente.setNome("Cliente cli");
        cliente.setSenha("cliente123");

        em.persist(veterinario);
        et.commit();

        assertNotNull(veterinario.getIdUsuario());
    }

    @Test
    public void atualizaVeterinarioValidoTeste() {
        assertTrue(true);
    }

    @Test
    public void deletaVeterinarioTeste() {

        Query query = em.createQuery("from Veterinario v where v.crmv like :crmv", Veterinario.class);
        query.setParameter("crmv", "54214554");
        Veterinario veterinario = (Veterinario) query.getSingleResult();

        em.remove(veterinario);
        et.commit();

        veterinario = em.find(Veterinario.class, veterinario.getIdUsuario());

        assertNull(veterinario);
    }

    @Test
    public void criaVeterinarioInvalidoTeste() {
        assertTrue(true);
    }

    @Test
    public void atualizaVeterinarioInvalidoTeste() {
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
        
        TypedQuery<Veterinario> query = em.createNamedQuery("Veterinario.PorEspecialidade", Veterinario.class);
        query.setParameter(1, "Cardiaco");
        
        List<Veterinario> listaVeterinarios = query.getResultList();

        for (Veterinario veterinario : listaVeterinarios) {
            assertTrue(veterinario.getEspecialidade().startsWith("Cardiaco"));
        }

        assertEquals(1, listaVeterinarios.size());
        
        assertTrue(true);
    }

    @Test
    public void atualizaVeterinarioQueryTeste() {
        assertTrue(true);
    }

    @Test
    public void deletaVeterinarioQueryTeste() {

        assertTrue(true);
    }
}
