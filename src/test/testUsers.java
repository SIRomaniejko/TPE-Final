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

import users.Grupo;
import users.Usuario;

public class testUsers {
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	@BeforeClass
	public static void setEntityManagerFactory() {
		emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		System.out.println("before class");
		List<Usuario> usuarios = new ArrayList<>();
		String csvFile = "src\\input\\usuario.csv";
		String line;
		String csvSplitBy = ",";
		Usuario aux;

		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
			while((line = br.readLine()) != null) {
				String[] items = line.split(csvSplitBy);
				aux = new Usuario(items[0], items[1], Integer.parseInt(items[2]), Double.parseDouble(items[4]), Double.parseDouble(items[5]));
				usuarios.add(aux);
			}
			System.out.println("Cargado Usuarios con exito");
		}catch(IOException e){
			e.printStackTrace();
		}
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (Usuario usuario: usuarios) {
			em.persist(usuario);
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
		List<Usuario> result  = query.setParameter(1, "Fenny").getResultList();
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
