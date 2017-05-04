package sistema.testes;

import com.sistema.model.ConsultaGeral;
import com.sistema.model.Servico;
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
 * @author Jonathan Romualdo
 */
public class ServicoTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

    public ServicoTest() {
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
    public void criaServicoValidoTest() {
        List<ConsultaGeral> listaConsultaGeral = new ArrayList<>();
        Servico servico = new Servico();

        servico.setNome("Banho");
        servico.setValor(100.00);
        servico.setListaConsultaGeral(listaConsultaGeral);

        em.persist(servico);
        assertNotNull(servico.getIdServico());
    }

    @Test
    public void criaServicoInvalidoTest() {

    }

    @Test
    public void atualizaServicoValidoTest() {

    }

    @Test
    public void atualizaServicoInvalidoTest() {

    }

}
