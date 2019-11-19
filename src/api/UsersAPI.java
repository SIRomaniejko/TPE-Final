package api;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import users.Usuario;

@Path("/users")
public class UsersAPI extends Api{
	@Path("/personas")
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
	@Path("/personas/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("id") int id) {
		Query busqueda = this.em.createNamedQuery("getByDni", Usuario.class);
		busqueda.setParameter(1, id);
		Usuario resultado = (Usuario)busqueda.getSingleResult();
		this.em.close();
		return resultado;
	}
	
	@Path("/personas")
	@PUT
	public Response updateUsuarios() {
		this.em.close();
		return Response.status(403).build();
	}
	
	@Path("/personas/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("id") int id, Usuario usuarioNuevo) {
		System.out.println("testingu");
		System.out.println(usuarioNuevo);
		System.out.println(usuarioNuevo.getApellido());
		System.out.println(usuarioNuevo.getNombre());
		System.out.println(usuarioNuevo.getUbicacion().getX());
		System.out.println(usuarioNuevo.getUbicacion().getY());
		this.em.close();
		return Response.status(403).build();
	}
}
