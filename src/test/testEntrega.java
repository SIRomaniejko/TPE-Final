package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
import puntos.PuntoRecoleccion;
import users.Grupo;
import users.Usuario;

public class testEntrega {
	
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void setEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		System.out.println("before class");
		
		Usuario personaDefault1 = new Usuario("jose", "olmedo", 1111, 1, 1);
		Usuario personaDefault2 = new Usuario("yisu", "crais", 2222, 1, 1);
		Usuario personaDefault3 = new Usuario("yisu", "cravvvis", 3333, 1, 1);
		Usuario personaDefault4 = new Usuario("ba", "ree", 4444, 1, 1);
		Usuario personaDefault5 = new Usuario("haha", "koko", 5555, 1, 1);
		Grupo grupoDefault1 = new Grupo("barrio", "los pibes", null, 1, 1);
		grupoDefault1.addIndividuo(personaDefault2);
		grupoDefault1.addIndividuo(personaDefault1);
		Residuo residuoDefault1 = new Residuo("botella", 1, 20, true);
		Residuo residuoDefault2 = new Residuo("tapita", 0.1, 5, true);
		Residuo residuoDefault3 = new Residuo("lata", 0.5, 50, true);
		Residuo residuoDefault4 = new Residuo("vidrio", 1, 3, true);
		Residuo residuoDefault5 = new Residuo("cajaCarton", 10, 15, true);
		ONG ongdefault1 = new ONG("garrahan");
		ongdefault1.addResiduos(residuoDefault2);
		PuntoRecoleccion puntoDefault = new PuntoRecoleccion(100,1,1);
		
		ResiduoRegistro rrDefault1 = new ResiduoRegistro(residuoDefault2, 2, personaDefault1, puntoDefault, new Date(2018, 1, 3));
		ResiduoRegistro rrDefault2 = new ResiduoRegistro(residuoDefault1, 6, personaDefault4, puntoDefault, new Date(2018, 1, 4));
		ResiduoRegistro rrDefault3 = new ResiduoRegistro(residuoDefault3, 3, personaDefault3, puntoDefault, new Date(2018, 1, 5));
		ResiduoRegistro rrDefault4 = new ResiduoRegistro(residuoDefault2, 1, personaDefault2, puntoDefault, new Date(2018, 1, 6));
		ResiduoRegistro rrDefault5 = new ResiduoRegistro(residuoDefault2, 2, personaDefault1, puntoDefault, new Date(2018, 1, 7));
		ResiduoRegistro rrDefault6 = new ResiduoRegistro(residuoDefault4, 2, personaDefault5, puntoDefault, new Date(2018, 1, 8));
		ResiduoRegistro rrDefault7 = new ResiduoRegistro(residuoDefault5, 9, personaDefault2, puntoDefault, new Date(2018, 1, 9));
		ResiduoRegistro rrDefault8 = new ResiduoRegistro(residuoDefault2, 7, personaDefault1, puntoDefault, new Date(2018, 1, 10));
		ResiduoRegistro rrDefault9 = new ResiduoRegistro(residuoDefault4, 4, personaDefault3, puntoDefault, new Date(2018, 1, 11));
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(personaDefault1);
		em.persist(personaDefault2);
		em.persist(personaDefault5);
		em.persist(personaDefault3);
		em.persist(personaDefault4);
		em.persist(grupoDefault1);
		em.persist(residuoDefault1);
		em.persist(residuoDefault2);
		em.persist(residuoDefault3);
		em.persist(residuoDefault4);
		em.persist(residuoDefault5);
		em.persist(ongdefault1);
		em.persist(puntoDefault);
		em.persist(rrDefault1);
		em.persist(rrDefault2);
		em.persist(rrDefault3);
		em.persist(rrDefault4);
		em.persist(rrDefault5);
		em.persist(rrDefault6);
		em.persist(rrDefault7);
		em.persist(rrDefault8);
		em.persist(rrDefault9);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@AfterClass
	public static void closeEntityManagerFactory() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Grupo").executeUpdate();
		em.createQuery("DELETE FROM ResiduoRegistro").executeUpdate();
		em.createQuery("DELETE FROM Usuario").executeUpdate();
		em.createQuery("DELETE FROM PuntoRecoleccion").executeUpdate();
		em.createQuery("DELETE FROM Residuo").executeUpdate();
		em.createQuery("DELETE FROM ONG").executeUpdate();
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
	public void testInicial() {
		Query query = em.createNamedQuery("getAllPuntos");
		List<PuntoRecoleccion> results = query.getResultList();
		System.out.println(results.get(0).getVolumenParaRecolectar());
		;
	}
	
	
}
