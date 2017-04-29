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
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class VeterinarioTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    public VeterinarioTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("exemplo_12");
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
            logger.log(Level.SEVERE, ex.getMessage());

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
        
        assertNotNull(veterinario.getIdUsuario());
    }

    @Test
    public void criaVeterinarioInvalidoTeste() {

    }

    @Test
    public void atualizaVeterinarioValidoTeste() {

    }

    @Test
    public void atualizaVeterinarioInvalidoTeste() {

    }

}
