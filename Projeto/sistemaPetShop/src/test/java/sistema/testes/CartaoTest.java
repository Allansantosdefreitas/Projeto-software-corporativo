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
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;


/**
 *
 * @author Usuario
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

    // OK
    // Cartao
    @Test
    public void criarCartaoValidoEmTest() {
        Cartao cartao = new Cartao();
        cartao.setBandeira("Visa Electron");
        cartao.setNumero("5559293458709012");

        Calendar calendario = Calendar.getInstance();
        calendario.set(2019, 5, 5);

        cartao.setDataValidade(calendario.getTime());
        em.persist(cartao);

        et.commit();

        cartao = em.find(Cartao.class, cartao.getIdCartao());
        assertNotNull(cartao.getIdCartao());
    }

    // OK
    // Cartao, Cliente
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
        cliente = (Cliente) query.getSingleResult();

        em.persist(cartao);
        et.commit();

        cartao = em.find(Cartao.class, cartao.getIdCartao());
        cliente = em.find(Cliente.class, cliente.getIdUsuario());

        assertNotNull(cartao.getIdCartao());
        assertNotNull(cliente.getIdUsuario());

    }
    
    
// fazendo ainda  
//    public void atualizarCartaoValidoNativeQueryTest() {
//        
//        Logger.getGlobal().log(Level.INFO, "atualizarCartaoValido");
//        
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
  
    
//    @Test
//    public void criarCartaoInvalidoEmTest() {
//        
//        Cartao cartao = new Cartao();
//        Calendar calendar = GregorianCalendar.getInstance();
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//        Calendar calendario = Calendar.getInstance();
//        calendario.set(2010, 6, 12); 
//        
//        cartao.setBandeira(null); // Inválido
//        cartao.setDataValidade(calendario.getTime());// Data inválida, pois é passada
//        cartao.setNumero("5559293778809777");
//       
//        Set<ConstraintViolation<Cartao>> constraintViolations = validator.validate(cartao);
//
//        if (logger.isLoggable(Level.INFO)) {
//            for (ConstraintViolation violation : constraintViolations) {
//                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//            }
//        }
//
//        assertEquals(2, constraintViolations.size());
//    }

//    /* FAIL */
//    @Test
//    public void criarCartaoInvalidoEmTestOLD() {
//
//        Cartao cartao = null;
//        Calendar calendario = null;
//
//        try {
//            cartao = new Cartao();
//            cartao.setBandeira("VISA");
//            cartao.setNumero(null); // Invalido
//            calendario = Calendar.getInstance();
//            calendario.set(2015, 5, 5);
//            cartao.setDataValidade(calendario.getTime()); // Data inválida (não é futura)
//
//            em.persist(cartao);
//            et.commit();
//
//            assertTrue(false); // Se chegar aqui, o teste falhou
//        } catch (ConstraintViolationException ex) {
//            Logger.getGlobal().info(ex.getMessage());
//
//            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
//
//            if (logger.isLoggable(Level.INFO)) {
//                for (ConstraintViolation violation : constraintViolations) {
//                    Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//                }
//            }
//
//            assertEquals(2, constraintViolations.size());
//            assertNull(cartao.getIdCartao());
//        }
//    }
    
    

//    @Test
//    public void atualizarCartaoInvalido() {
//        
//        Logger.getGlobal().log(Level.INFO, "atualizarCartaoInvalido");
//        
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
//

    /* OK */
    // Cartao
    @Test
    public void deletarCartaoEmTest() {

        Logger.getGlobal().log(Level.INFO, "deletarCartaoTest");
        TypedQuery<Cartao> query = em.createQuery("SELECT c FROM Cartao c WHERE c.numero like :numeroCartao", Cartao.class);
        query.setParameter("numeroCartao", "1119293458709222");
        Cartao cartao = query.getSingleResult();

        em.remove(cartao);
        et.commit();

        cartao = em.find(Cartao.class, cartao.getIdCartao());
        assertNull(cartao);

    }

    
    /* OK */
    @Test
    public void deletarCartaoQueryTest() {

        Logger.getGlobal().log(Level.INFO, "deletarCartaoTest");
        Long id = 1L;
        Query query = em.createQuery("DELETE FROM Cartao AS c WHERE c.idCartao = ?1");
        query.setParameter(1, id);
        query.executeUpdate();

        Cartao cartao = em.find(Cartao.class, id);
        assertNull(cartao);

    }
    
    
    /* OK */
    @Test
    public void atualizarCartaoQueryTest() {
        Logger.getGlobal().log(Level.INFO, "atualizarCartaoQueryTest");
        
        Long id = 1L;
        Query query = em.createQuery("UPDATE Cartao AS c SET c.bandeira = ?1 WHERE c.idCartao = ?2");
        
        String bandeira = "Master Card";
        
        query.setParameter(1, bandeira);
        query.setParameter(2, id);
        query.executeUpdate();
        
        Cartao cartao = em.find(Cartao.class, id);
        
        assertEquals(bandeira, cartao.getBandeira());
     
    }
}
