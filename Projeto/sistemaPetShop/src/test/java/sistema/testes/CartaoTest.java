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
import java.util.Calendar;

/**
 *
 * @author Usuario
 */
public class CartaoTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction et;

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
    
    @Test
    public void criarCartaoInvalidoTest() {
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

}
