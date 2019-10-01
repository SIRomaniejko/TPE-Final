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
	private Usuario personaDefault = new Usuario("yisu", "crais", 3224, "domicilio", 1, 1);
	@BeforeClass
	public static void setEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		System.out.println("before class");
	}
	@AfterClass
	public static void closeEntityManagerFactory() {
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
	public void persistirPersona() {
		/*em.getTransaction().begin();;
		em.persist(personaDefault);
		em.getTransaction().commit();
		System.out.println("test1");*/
		assertNotNull(em.find(Usuario.class, "3224"));
		System.out.println("test2");
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
	

}
