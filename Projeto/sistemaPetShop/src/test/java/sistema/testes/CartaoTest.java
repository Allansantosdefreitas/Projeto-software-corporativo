/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.testes;

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
import com.sistema.model.Cartao;
import com.sistema.model.Cliente;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Usuario
 *
 * 1- Insert, Update, delete (via em) 2- Select (JPQL) query string named query
 * ex.: "checar qtde valores etc." 3- Select (SQL native query) query string
 * named query recuperar objetos Recuperar objetos e valor ex.: "checar qtde
 * valores etc." 4- Update, delete (via query)
 *
 */
public class CartaoTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;
    private static Logger logger;

    public CartaoTest() {
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
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);

        emf = Persistence.createEntityManagerFactory("sistemapetshopPU"); // nome da PU
        DbUnitUtil.inserirDados();

        em = emf.createEntityManager();
        et = em.getTransaction();
        et.begin();
    }

    @After
    public void tearDown() {
        try {
            //et.commit();
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

    // OK
    @Test
    public void criarCartaoValidoTest() {
        Cartao cartao = new Cartao();
        cartao.setBandeira("Visa Electron");
        cartao.setNumero("5559293458709012");

        Calendar calendario = Calendar.getInstance();
        calendario.set(2019, 5, 5);

        cartao.setDataValidade(calendario.getTime());
        em.persist(cartao);

        et.commit();
        assertNotNull(cartao.getIdCartao());
    }

    // OK
    @Test
    public void criarCartaoValidoQueryTest() {

        Cartao cartao = new Cartao();
        Cliente cliente = new Cliente();
        cartao.setBandeira("American Express");

        Calendar calendario = Calendar.getInstance();
        calendario.set(2020, 6, 12);

        cartao.setDataValidade(calendario.getTime());
        cartao.setNumero("5559293778809012");

        TypedQuery<Cliente> query = em.createQuery("FROM Cliente c WHERE c.idUsuario = :idCliente", Cliente.class);
        query.setParameter("idCliente", 1L);
        cliente = (Cliente)query.getSingleResult();
        
        em.persist(cartao);
//        assertNotNull(comprador.getId());
        et.commit();
        assertNotNull(cartao.getIdCartao());
        assertNotNull(cliente.getIdUsuario());

    }

    /* FAIL */
    @Test
    public void criarCartaoInvalidoTest() {

        Cartao cartao = null;
        Calendar calendario = null;

        try {
            cartao = new Cartao();
            cartao.setBandeira("VISA");
            cartao.setNumero(null); // Invalido
            calendario = Calendar.getInstance();
            calendario.set(2015, 5, 5);
            cartao.setDataValidade(calendario.getTime()); // Data inválida (não é futura)

            em.persist(cartao);
            et.commit();

            assertTrue(false); // Se chegar aqui, o teste falhou
        } catch (ConstraintViolationException ex) {
            Logger.getGlobal().info(ex.getMessage());

            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

            if (logger.isLoggable(Level.INFO)) {
                for (ConstraintViolation violation : constraintViolations) {
                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
                }
            }

            assertEquals(2, constraintViolations.size());
            assertNull(cartao.getIdCartao());
        }

    }

//    @Test
//    public void t05_atualizarCompradorInvalido() {
//        Logger.getGlobal().log(Level.INFO, "t05_criarCompradorInvalido");
//        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.cpf like :cpf", Usuario.class);
//        query.setParameter("cpf", "787.829.223-06");
//        Usuario usuario = query.getSingleResult();
//        usuario.setSenha("testando1234");
//
//        try {
//            em.flush();
//            assertTrue(false);
//        } catch (ConstraintViolationException ex) {
//            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//
//            if (logger.isLoggable(Level.INFO)) {
//                for (ConstraintViolation violation : constraintViolations) {
//                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//                }
//            }
//
//            assertEquals(1, constraintViolations.size());
//        }
//    }

    /* OK */
    @Test
    public void deletarCartaoTest() {

        Logger.getGlobal().log(Level.INFO, "deletarCartaoTest");
        TypedQuery<Cartao> query = em.createQuery("SELECT c FROM Cartao c WHERE c.numero like :numeroCartao", Cartao.class);
        query.setParameter("numeroCartao", "1119293458709222");
        Cartao cartao = query.getSingleResult();

        em.remove(cartao);
        et.commit();

        cartao = em.find(Cartao.class, cartao.getIdCartao());
        assertNull(cartao);

    }

}
