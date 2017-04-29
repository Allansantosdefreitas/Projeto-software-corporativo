package com.sistema.test;

import com.sistema.model.Cartao;
import com.sistema.model.Usuario;
import com.sistema.model.Cliente;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import sistema.testes.DbUnitUtil;

/**
 *
 * @author MASC, Allan Santos
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JpqlTest {

    private static EntityManagerFactory emf;
    private static Logger logger;
    private EntityManager em;
    private EntityTransaction et;

    @BeforeClass
    public static void setUpClass() {
        logger = Logger.getGlobal();
        logger.setLevel(Level.INFO);
        emf = Persistence.createEntityManagerFactory("sistemapetshopPU");
        DbUnitUtil.inserirDados();
    }

    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    @Before
    public void setUp() {
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
    public void t01_criarCartaoValido() {

        Cartao cartao = new Cartao();
        
        /* Preenchendo atributos --------------  */
        cartao.setBandeira("MasterCard");
        
        Calendar calendario = Calendar.getInstance();
        calendario.set(2018, 12, 15);
        
        cartao.setDataValidade(calendario.getTime());
        cartao.setNumero("1119293458709222");
        
        //cartao.setCliente(cliente); // do jeito que tá mapeado, cliente é opcional
                
        em.persist(cartao);
        assertNotNull(cartao.getIdCartao());
    }
    
    
//
//    @Test //Usuario, Vendedor, Endereco
//    public void t02_criarVendedorInvalido() {
//        Vendedor vendedor = null;
//        Calendar calendar = new GregorianCalendar();
//        try {
//            vendedor = new Vendedor();
//            vendedor.setCpf("258.171.482-34"); //CPF inválido
//            calendar.set(2020, Calendar.JANUARY, 1);
//            //Data de nascimento inválida            
//            vendedor.setDataNascimento(calendar.getTime());
//            vendedor.setEmail("email_invalido@"); //E-mail inválido
//            vendedor.setLogin("fulano_silva");
//            vendedor.setPrimeiroNome("FULANO"); //Nome inválido
//            vendedor.setUltimoNome("silva"); //Nome inválido
//            vendedor.setReputacao(Reputacao.NOVATO);
//            vendedor.setSenha("testando1234."); //Senha inválida
//            vendedor.setValorVendas(0.0);
//            vendedor.addTelefone("(81)9234-5675"); //Quantidade inválida de telefones
//            vendedor.addTelefone("(81)9234-5676");
//            vendedor.addTelefone("(81)9234-5677");
//            vendedor.addTelefone("(81)9234-5678");
//            Endereco endereco = vendedor.criarEndereco();
//            endereco.setBairro("CDU");
//            endereco.setCep("50670-230"); //CEP inválido
//            endereco.setCidade("Recife");
//            endereco.setEstado("Pernambuco"); //Estado inválido
//            endereco.setNumero(20);
//            endereco.setComplemento("AP 301");
//            endereco.setLogradouro("Av. Professor Moraes Rego");
//            em.persist(vendedor);
//            assertTrue(false);
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
//            assertEquals(10, constraintViolations.size());
//            assertNull(vendedor.getId());
//        }
//    }
//
//    @Test //Comprador, CartaoCredito
//    public void t03_criarCompradorValido() {
//        Comprador comprador = new Comprador();
//        CartaoCredito cartaoCredito = new CartaoCredito();
//        cartaoCredito.setNumero("4929293458709012");
//        cartaoCredito.setBandeira("VISA");
//        Calendar calendar = GregorianCalendar.getInstance();
//        calendar.set(2018, Calendar.DECEMBER, 1);
//        cartaoCredito.setDataExpiracao(calendar.getTime());
//        comprador.setCartaoCredito(cartaoCredito);
//        comprador.setCpf("453.523.472-81");
//        calendar.set(1985, Calendar.JANUARY, 1);
//        comprador.setDataNascimento(calendar.getTime());
//        comprador.setEmail("comprador@gmail.com");
//        comprador.setPrimeiroNome("Maria");
//        comprador.setUltimoNome("Silva");
//        comprador.setLogin("comprador_bom");
//        comprador.setSenha("m1nhAs3nh4.");
//        Endereco endereco = comprador.criarEndereco();
//        endereco.setBairro("CDU");
//        endereco.setCep("50.670-230");
//        endereco.setCidade("Recife");
//        endereco.setEstado("PE");
//        endereco.setNumero(20);
//        endereco.setComplemento("AP 301");
//        endereco.setLogradouro("Av. Professor Moraes Rego");
//
//        TypedQuery query
//                = em.createQuery("SELECT i FROM Item i WHERE i.titulo LIKE ?1", Item.class);
//        query.setParameter(1, "boss DD-7");
//        Item item = (Item) query.getSingleResult();
//        Oferta oferta = new Oferta();
//        oferta.setValor(new BigDecimal("575.50"));
//        item.adicionar(oferta);
//        comprador.adicionar(oferta);
//        em.persist(comprador);
//        assertNotNull(comprador.getId());
//        assertNotNull(cartaoCredito.getId());
//        assertNotNull(oferta.getId());
//    }
//
//    @Test //Comprador, CartaoCredito
//    public void t04_criarCompradorInvalido() {
//        Comprador comprador = new Comprador();
//        CartaoCredito cartaoCredito = new CartaoCredito();
//        Calendar calendar = GregorianCalendar.getInstance();
//        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
//
//        //CPF inválido
//        comprador.setCpf("453.123.472-11");
//        calendar.set(1985, Calendar.JANUARY, 1);
//        comprador.setDataNascimento(calendar.getTime());
//        comprador.setEmail("comprador@gmail.com");
//        //Primeiro nome inválido
//        comprador.setPrimeiroNome("maria");
//        comprador.setUltimoNome("Silva");
//        comprador.setLogin("comprador_bom");
//        comprador.setSenha("m1nhAs3nh4.");
//        Endereco endereco = comprador.criarEndereco();
//        endereco.setBairro("CDU");
//        endereco.setCep("50.670-230");
//        endereco.setCidade("Recife");
//        endereco.setEstado("AA");
//        endereco.setNumero(20);
//        endereco.setComplemento("AP 301");
//        endereco.setLogradouro("Av. Professor Moraes Rego");
//        //Nº inválido do cartão de crédito
//        cartaoCredito.setNumero("1929293458709012");
//        //Bandeira inválida
//        cartaoCredito.setBandeira(null);
//        //Data de expiração inválida (deveria ser data passada).
//        calendar.set(2014, Calendar.DECEMBER, 1);
//        cartaoCredito.setDataExpiracao(calendar.getTime());
//        comprador.setCartaoCredito(cartaoCredito);
//        Set<ConstraintViolation<Comprador>> constraintViolations = validator.validate(comprador);
//
//        if (logger.isLoggable(Level.INFO)) {
//            for (ConstraintViolation violation : constraintViolations) {
//                Logger.getGlobal().log(Level.INFO, "{0}.{1}: {2}", new Object[]{violation.getRootBeanClass(), violation.getPropertyPath(), violation.getMessage()});
//            }
//        }
//
//        assertEquals(6, constraintViolations.size());
//    }
//
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
}
