package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import users.Usuario;

public class testUsers {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Usuario personaDefault = new Usuario("nombre", "apellido", 3222, "domicilio", 1, 1);
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
		em.getTransaction().begin();;
		em.persist(personaDefault);
		em.getTransaction().commit();;
		System.out.println("test1");
		assertNotNull(em.find(Usuario.class, 3222));
		System.out.println("test2");
	}
	
	

}
