package testAPI;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebResult;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.glassfish.jersey.client.*;


import basura.Residuo;
import basura.ResiduoRegistro;
import contenedorRespuestas.ONGs;
import contenedorRespuestas.PuntosRecoleccion;
import contenedorRespuestas.Residuos;
import contenedorRespuestas.Usuarios;
import organizaciones.ONG;
import puntos.PuntoRecoleccion;
import users.Usuario;

public class testEntrega {

	private static Client client;
	private static WebTarget target;
	private static Response responseONGdef;
	private static Response responseUsuarioDef1;
	private static Response responseUsuarioDef2;
	private static Response responsePuntoDef;
	private static Response responseResiduoDef1;
	private static Response responseResiduoDef2;
	private static Response responseResiduoDef3;
	
	@BeforeClass
	public static void agregarCosas() {
		client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080/TPE-Final/api");
		
		//creamos una ONG
		WebTarget tarjetLocal = target.path("ONG");
		Invocation.Builder invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
		responseONGdef = invocationBuilder.post(Entity.entity(new ONG("caritas"), MediaType.APPLICATION_JSON));
		
		//creamos Usuarios
		tarjetLocal = target.path("users");
		invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(new Usuario("jose", "carlos", 232, 10, 20), MediaType.APPLICATION_JSON));
		responseUsuarioDef1 = invocationBuilder.post(Entity.entity(new Usuario("pablo", "olmedo", 233, 10, 21), MediaType.APPLICATION_JSON));
		responseUsuarioDef2 = invocationBuilder.post(Entity.entity(new Usuario("martin", "rodriguez", 240, 18, 21), MediaType.APPLICATION_JSON));
		response = invocationBuilder.post(Entity.entity(new Usuario("Rodolfo", "Gutierrez", 250, 11, 21), MediaType.APPLICATION_JSON));
		response = invocationBuilder.post(Entity.entity(new Usuario("roberto", "alvarez", 232, 10, 25), MediaType.APPLICATION_JSON));
		
		//creamos puntos de recoleccion
		tarjetLocal = target.path("PuntoRecoleccion");
		invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
		responsePuntoDef = invocationBuilder.post(Entity.entity(new PuntoRecoleccion(50, 20, 50), MediaType.APPLICATION_JSON));
		response = invocationBuilder.post(Entity.entity(new PuntoRecoleccion(300, 90, 78), MediaType.APPLICATION_JSON));
		response = invocationBuilder.post(Entity.entity(new PuntoRecoleccion(20, 10, 43), MediaType.APPLICATION_JSON));
		
		//creamos Residuos
		tarjetLocal = target.path("residuo");
		invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
		ONG ongSubida = responseONGdef.readEntity(ONG.class);
		Residuo residuoNuevo = new Residuo("botella", 3, 20, true);
		residuoNuevo.setOngPertenece(ongSubida);
		responseResiduoDef1 = invocationBuilder.post(Entity.entity(residuoNuevo, MediaType.APPLICATION_JSON));
		residuoNuevo = new Residuo("tapita", 0.1, 50, true);
		residuoNuevo.setOngPertenece(ongSubida);
		responseResiduoDef2 = invocationBuilder.post(Entity.entity(residuoNuevo, MediaType.APPLICATION_JSON));
		residuoNuevo = new Residuo("organicos", 10, 1, false);
		residuoNuevo.setOngPertenece(ongSubida);
		responseResiduoDef3 = invocationBuilder.post(Entity.entity(residuoNuevo, MediaType.APPLICATION_JSON));
		residuoNuevo = new Residuo("rueda", 40, 2000, true);
		residuoNuevo.setOngPertenece(ongSubida);
		response = invocationBuilder.post(Entity.entity(residuoNuevo, MediaType.APPLICATION_JSON));
		residuoNuevo = new Residuo("chatarra", 1, 100, true);
		residuoNuevo.setOngPertenece(ongSubida);
		response = invocationBuilder.post(Entity.entity(residuoNuevo, MediaType.APPLICATION_JSON));
		
		//creamos registros
		tarjetLocal = target.path("registro");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		Residuo residuoDef1 = responseResiduoDef1.readEntity(Residuo.class);
		Residuo residuoDef2 = responseResiduoDef2.readEntity(Residuo.class);
		Residuo residuoDef3 = responseResiduoDef3.readEntity(Residuo.class);
		Usuario userDef1 = responseUsuarioDef1.readEntity(Usuario.class);
		Usuario userDef2 = responseUsuarioDef2.readEntity(Usuario.class);
		PuntoRecoleccion puntoDef1 = responsePuntoDef.readEntity(PuntoRecoleccion.class);
		System.out.println(residuoDef1 != null && userDef1 != null && puntoDef1 != null);
		ResiduoRegistro rr = new ResiduoRegistro(residuoDef1, 1, userDef1, puntoDef1, new Date(120, 0, 2));
		response = invocationBuilder.post(Entity.entity(rr, MediaType.APPLICATION_JSON));
		System.out.println("acaso funciona?" + response.getStatus());
	}
	
	
	
//	@Test
//	public void addONG() {
//		WebTarget tarjetLocal = target.path("ONG");
//		Invocation.Builder invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
//		Response response = invocationBuilder.post(Entity.entity(new ONG("MAGIA DO CARITinhoshi"), MediaType.APPLICATION_JSON));
//		System.out.println(response.getStatus());
//		System.out.println(response.readEntity(ONG.class).getId());
//	}
	
	@Test
	public void checkCantidad() {
		//check cantidad ONG
		WebTarget tarjetLocal = target.path("ONG");
		Invocation.Builder invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		ONGs resultado = response.readEntity(ONGs.class);
		assertEquals(1, resultado.size());
		
		//check cantidad usuarios
		tarjetLocal = target.path("users");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();
		Usuarios resultadoUser = response.readEntity(Usuarios.class);
		assertEquals(5, resultadoUser.size());
		
		//check cantidad puntosRecoleccion
		tarjetLocal = target.path("PuntoRecoleccion");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();
		PuntosRecoleccion resultadoPunto = response.readEntity(PuntosRecoleccion.class);
		assertEquals(3, resultadoPunto.size());
		
		//check cantidad Residuos
		tarjetLocal = target.path("residuo");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.get();
		Residuos resultadoResiduo = response.readEntity(Residuos.class);
		assertEquals(5, resultadoResiduo.size());
	}
	
	@AfterClass
	public static void RemoveAll() {
		//remove Residuo
		WebTarget tarjetLocal = target.path("residuo");
		Invocation.Builder invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.delete();
		
		//remove ONG
		tarjetLocal = target.path("ONG");
		invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.delete();
		
		//remove PuntoRecoleccion
		tarjetLocal = target.path("PuntoRecoleccion");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.delete();
		
		//remove Usuarios
		tarjetLocal = target.path("users");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		response = invocationBuilder.delete();
	}
}
