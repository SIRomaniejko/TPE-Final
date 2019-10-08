package test;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

public class testResiduo {
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void setEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		System.out.println("before class");
		List<Residuo> residuos = new ArrayList<>();
		String csvFile = "src\\input\\residuo.csv";
		String line;
		String csvSplitBy = ",";
		Residuo aux;

		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			while((line = br.readLine()) != null) {
				String[] items = line.split(csvSplitBy);
				aux = new Residuo(items[0], Double.parseDouble(items[1]), Double.parseDouble(items[2]), Boolean.parseBoolean(items[3]));
				residuos.add(aux);
			}
			System.out.println("Cargado Residuos con exito");
		}catch(IOException e){
			e.printStackTrace();
		}

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Residuo residuo: residuos) {
			em.persist(residuo);
		}
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
		Query query1 = em.createNamedQuery("getAllResiduos");
		List<Residuo> result = query1.getResultList();
		for(Residuo residuo: result) {
			System.out.println(residuo.getNombre());
		}
		assertNotEquals(0, result.size());
	}
}
