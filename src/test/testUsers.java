package test;

import static org.junit.Assert.*;

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

import users.Usuario;

public class testUsers {
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	
	@BeforeClass
	public static void setEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		System.out.println("before class");
		
		Usuario personaDefault1 = new Usuario("jose", "olmedo", 1111, "domicilio", 1, 1);
		Usuario personaDefault2 = new Usuario("yisu", "crais", 2222, "domicilio", 1, 1);
		Usuario personaDefault3 = new Usuario("yisu", "cravvvis", 3333, "domicilio", 1, 1);
		Usuario personaDefault4 = new Usuario("ba", "ree", 4444, "domicilio", 1, 1);
		Usuario personaDefault5 = new Usuario("haha", "koko", 5555, "domicilio", 1, 1);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(personaDefault1);
		em.persist(personaDefault2);
		em.persist(personaDefault5);
		em.persist(personaDefault3);
		em.persist(personaDefault4);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@AfterClass
	public static void closeEntityManagerFactory() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Usuario").executeUpdate();
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
	public void getAll() {
		Query query1 = em.createNamedQuery("getAll");
		List<Usuario> result = query1.getResultList();
		for(Usuario user: result) {
			System.out.println(user.getNombre());
		}
		assertNotEquals(0, result.size());
		
	}
	
	@Test
	public void getByName() {
		Query query = em.createNamedQuery("getByName");
		List<Usuario> result  = query.setParameter(1, "yisu").getResultList();
		System.out.println(result.toString());
		for(Usuario user: result) {
			System.out.println(user.getNombre());
			System.out.println(user.getApellido());
		}
		assertNotEquals(0, result.size());
	}
	@Test
	public void getNombre() {
		Query query = em.createNamedQuery("getNombre");
		String nombre = (String) query.setParameter(1, 3333).getSingleResult();
		assertNotNull(nombre);
		System.out.println(nombre + "xDDD");
	}
}
