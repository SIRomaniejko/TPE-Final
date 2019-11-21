package api;

import java.util.List;

import javax.persistence.NoResultException;
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

import organizaciones.ONG;
import users.Usuario;

@Path("/ONG")
public class OngAPI extends Api{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ONG> getAllONG(){
		Query busqueda = this.em.createNamedQuery("getAllONG");
		List<ONG> resultado = null;
		try {
			resultado = busqueda.getResultList();
		}
		catch(NoResultException exc) {
		}
		this.em.close();
		return resultado;
		
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ONG getONG(@PathParam("id") int id) {
		ONG regreso = this.em.find(ONG.class, id);
		this.em.close();
		return regreso;
	}
	
	@PUT
	public Response updateONG() {
		this.em.close();
		return Response.status(403).build();
	}
	
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("id") int id, ONG cambios) {
		ONG ongPersistida = this.em.find(ONG.class, id);
		ongPersistida.copyONG(cambios);
		this.em.getTransaction().begin();
		this.em.merge(ongPersistida);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(ongPersistida).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUsuario(ONG ONGnueva) {
		this.em.getTransaction().begin();
		em.persist(ONGnueva);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(ONGnueva).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteONGs() {
		this.em.getTransaction().begin();
		Query remover = this.em.createNamedQuery("deleteAllONG");
		remover.executeUpdate();
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteONG(@PathParam("id") String id) {
		ONG a = this.em.find(ONG.class, id);
		this.em.getTransaction().begin();
		this.em.remove(a);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(a).build();
	}
}
