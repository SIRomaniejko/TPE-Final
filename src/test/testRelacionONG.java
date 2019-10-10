package test;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import basura.Residuo;
import basura.ResiduoRegistro;
import organizaciones.ONG;
import users.Usuario;

public class testRelacionONG {
	private static EntityManagerFactory emf;
	private EntityManager em;
	static Residuo residuo = new Residuo("tapita", 1, 1000, true);
	static ONG ong = new ONG("carinias");
	
	
	@BeforeClass
	public static void setEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		System.out.println("before class");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		residuo.setOngPertenece(ong);
		System.out.println(residuo.getOngPertenece() != null);
		em.persist(residuo);
		em.persist(ong);
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
	public void laRePutaMadre() {
		System.out.println("concha de tu hermana puta");
		System.out.println(((Residuo) em.createQuery("SELECT c FROM Residuo c").getSingleResult()).getOngPertenece() != null);
		System.out.println(((ONG) em.createQuery("SELECT a FROM ONG a").getResultList().get(0)).noEsVacio());
	}
}
