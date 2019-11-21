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

import users.Grupo;
import users.Usuario;

public class testGrupo {
	
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
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(personaDefault1);
		em.persist(personaDefault2);
		em.persist(personaDefault5);
		em.persist(personaDefault3);
		em.persist(personaDefault4);
		em.persist(grupoDefault1);
		em.getTransaction().commit();
		em.close();
	}
	
	
	@AfterClass
	public static void closeEntityManagerFactory() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Usuario").executeUpdate();
		em.createQuery("DELETE FROM Grupo").executeUpdate();
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

}
