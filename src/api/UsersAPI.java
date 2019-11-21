package api;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
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

import users.Usuario;

@Path("/users")
public class UsersAPI extends Api{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getAllUsuarios(){
		Query busqueda = this.em.createNamedQuery("getAllUsers");
		try {
			List<Usuario> resultado = busqueda.getResultList();
			this.em.close();
			return resultado;
		}
		catch(NoResultException exc) {
			this.em.close();
			return null;
		}
		
	}
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("id") int id) {
		Query busqueda = this.em.createNamedQuery("getByDni", Usuario.class);
		busqueda.setParameter(1, id);
		Usuario resultado = null;
		try {
			resultado = (Usuario)busqueda.getSingleResult();
		}
		catch(NoResultException exc) {
		}
		this.em.close();
		return resultado;
	}
	
	
	@PUT
	public Response updateUsuarios() {
		this.em.close();
		return Response.status(403).build();
	}
	
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("id") String id, Usuario usuarioNuevo) {
		this.em.getTransaction().begin();
		Usuario usuario = this.em.find(Usuario.class, id);
		usuario.copyUser(usuarioNuevo);
		em.merge(usuario);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(usuario).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postUsuario(Usuario usuarioNuevo) {
		this.em.getTransaction().begin();
		em.persist(usuarioNuevo);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(usuarioNuevo).build();
	}
	
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuarios() {
		try {
			this.em.getTransaction().begin();
			this.em.createNamedQuery("deleteAllUsers").executeUpdate();
			this.em.getTransaction().commit();
			this.em.close();			
		}
		catch (PersistenceException e) {
			this.em.close();
			return Response.status(409).build();
		}
		return Response.status(200).build();
	}
	
	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(@PathParam("id") String id) {
		
		Usuario a = this.em.find(Usuario.class, id);
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
