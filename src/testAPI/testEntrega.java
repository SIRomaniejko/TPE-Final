package testAPI;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import basura.Residuo;
import organizaciones.ONG;

public class testEntrega {

	private static EntityManagerFactory emf;
	private EntityManager em;
	static Residuo residuo = new Residuo("tapita", 1, 1000, true);
	static ONG ong = new ONG("carinias");
	
	
	@BeforeClass
	public static void agregarCosas() {
		System.out.println("GUREIT");
	}
	
	
	@AfterClass
	public static void borrarCosas() {
		System.out.println("GUREITO");
	}
	
	
	@Test
	public void agregarONG() {
		
	}
}
