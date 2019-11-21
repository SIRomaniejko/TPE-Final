package api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import basura.Residuo;
import basura.ResiduoRegistro;
import puntos.PuntoRecoleccion;
import users.Usuario;

@Path("PuntoRecoleccion")
public class RecoleccionAPI extends Api{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PuntoRecoleccion> getAllPuntos(){
		Query get = this.em.createNamedQuery("getAllPuntos");
		List<PuntoRecoleccion> resultado = get.getResultList();
		this.em.close();
		return resultado;
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public PuntoRecoleccion getPuntoRecoleccion(@PathParam("id") int id) {
		PuntoRecoleccion resultado = this.em.find(PuntoRecoleccion.class, id);
		this.em.close();
		return resultado;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postPuntoRecoleccion(PuntoRecoleccion puntoNuevo) {
		this.em.getTransaction().begin();
		puntoNuevo.setResiduos(new ArrayList<ResiduoRegistro>());
		em.persist(puntoNuevo);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(puntoNuevo).build();
	}
	
	@PUT
	public Response updatePuntos() {
		this.em.close();
		return Response.status(403).build();
	}
	
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updatePunto(@PathParam("id") int id, PuntoRecoleccion puntoNuevo) {
		PuntoRecoleccion puntoPersistido = this.em.find(PuntoRecoleccion.class, id);
		if(puntoPersistido == null) {
			return Response.status(400).build();
		}
		puntoPersistido.copy(puntoNuevo);
		this.em.getTransaction().begin();
		this.em.merge(puntoPersistido);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(puntoPersistido).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removePuntos() {
		try {
			this.em.getTransaction().begin();
			Query remover = this.em.createNamedQuery("deleteAllPuntosRecoleccion");
			remover.executeUpdate();
			this.em.getTransaction().commit();			
		}
		catch (PersistenceException e) {
			this.em.close();
			return Response.status(409).build();
		}
		this.em.close();
		return Response.status(200).build();
		
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteResiduo(@PathParam("id") String id) {
		
		PuntoRecoleccion a = this.em.find(PuntoRecoleccion.class, id);
		if(a == null) {
			return Response.status(404).build();
		}
		try {
			this.em.getTransaction().begin();
			this.em.remove(a);
			this.em.getTransaction().commit();			
		}
		catch (PersistenceException e) {
			this.em.close();
			return Response.status(409).build();
		}
		this.em.close();
		return Response.status(200).entity(a).build();
	}
}

