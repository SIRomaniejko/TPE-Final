package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import basura.Residuo;
import basura.ResiduoRegistro;
import organizaciones.ONG;
import users.Usuario;

public class testResiduosGanancias {
	private static EntityManagerFactory emf;
	private EntityManager em;
	static Usuario user1 = new Usuario("jose", "pedro", 2, "casa", 1, 1);
	static Usuario user2 = new Usuario("pablo", "pedro", 3, "casa", 1, 1);
	static Usuario user3 = new Usuario("pedro", "pedro", 4, "casa", 1, 1);
	static Residuo residuoa = new Residuo("botella", 10, 100, true);
	static Residuo residuob = new Residuo("tapita", 1, 1000, true);
	static ResiduoRegistro residuoRegistro = new ResiduoRegistro(residuoa, 1, user1, null, new Date(2000, 1, 3));
	static ResiduoRegistro residuoRegistro2 = new ResiduoRegistro(residuoa, 5, user1, null, new Date(2000, 1, 3));
	static ResiduoRegistro residuoRegistro3 = new ResiduoRegistro(residuoa, 3, user2, null, new Date(2000, 1, 3));
	static ResiduoRegistro residuoRegistro4 = new ResiduoRegistro(residuob, 3, user1, null, new Date(2000, 1, 3));
	static ONG ong = new ONG("carinias");
	static ONG ong2 = new ONG("carotas");
	
	
	@BeforeClass
	public static void setEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		System.out.println("before class");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ong.addResiduos(residuoa);
		ong2.addResiduos(residuob);
		em.persist(user1);
		em.persist(user2);
		em.persist(user3);
		em.persist(residuoa);
		em.persist(residuob);
		em.persist(residuoRegistro);
		em.persist(residuoRegistro2);
		em.persist(residuoRegistro3);
		em.persist(residuoRegistro4);
		em.persist(ong);
		em.persist(ong2);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@AfterClass
	public static void closeEntityManagerFactory() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM ResiduoRegistro").executeUpdate();
		em.createQuery("DELETE FROM Usuario").executeUpdate();
		em.createQuery("DELETE FROM Residuo").executeUpdate();
		em.getTransaction().commit();
		em.close();
		emf.close();
		System.out.println("afterclass");
	}
	@Before
	public void openEntityManager() {
		em = emf.createEntityManager();
		System.out.println("before");
	}
	@After
	public void closeEntityManager() {
		em.close();
		System.out.println("after");
	}

	@Test
	public void testCantidad() {
		Query query = em.createQuery("SELECT SUM(rr.cantidad) FROM ResiduoRegistro rr");
		System.out.println(query.getSingleResult());
	}
	
	@Test
	public void testCantidadUser() {
		Query query = em.createQuery("SELECT SUM(rr.cantidad) FROM ResiduoRegistro rr WHERE rr.usuario = ?1");
		query.setParameter(1, user1);
		System.out.println("para user1");
		System.out.println(query.getSingleResult());
	}
	
	@Test
	public void testGanancia() {
		Query query = em.createQuery("SELECT SUM(rr.cantidad) * MAX(r.valor) FROM ResiduoRegistro rr JOIN rr.residuo r GROUP BY rr.residuo");
		System.out.println("ganancias");
		for(Object resultado: query.getResultList()) {
			System.out.println(resultado);
		}
	}
	
	@Test
	public void testGananciaUsuario() {
		Query query = em.createQuery("SELECT SUM(rr.cantidad) * MAX(r.valor) FROM ResiduoRegistro rr JOIN rr.residuo r WHERE rr.usuario = ?1 GROUP BY rr.residuo");
		query.setParameter(1, user1);
		System.out.println("ganancias user");
		for(Object resultado: query.getResultList()) {
			System.out.println(resultado);
		}
	}
	
	@Test
	public void testGananciaUsuarioTotal() {
		Query query = em.createNamedQuery("getAhorroUsuarioTotal");
		System.out.println("ganancias user TOTAL");
		query.setParameter(1, user1);
		double total = 0;
		for(Object resultado: query.getResultList()) {
			total += (Double)resultado;
		}
		System.out.println(total);
	}
	
	@Test
	public void testGananciaAndName() {
		Query query = em.createQuery("SELECT SUM(rr.cantidad) * MAX(r.valor), r.nombre FROM ResiduoRegistro rr JOIN rr.residuo r WHERE rr.usuario = ?1 GROUP BY rr.residuo, r.nombre");
		query.setParameter(1, user1);
		System.out.println("ganancias and producto");
		List<Object[]> resultado = query.getResultList();
		for(Object[] reciclado: resultado) {
			System.out.println(reciclado[0]);
			System.out.println(reciclado[1]);
		}
	}
	
	@Test 
	public void testGananciasONG() {
		System.out.println("sizeong");
		System.out.println(((ONG) em.createQuery("SELECT c FROM ONG c").getResultList().get(0)).getResiduos().size());
		Query query = em.createNamedQuery("getAhorroAONG");
		query.setParameter(1, user1);
		List<Object[]> resultado = query.getResultList();
		System.out.println("pwease owo");
		for(Object[] reciclado: resultado) {
			System.out.println(reciclado[0]);
			System.out.println(reciclado[1]);
		}
	}
}
