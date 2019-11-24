package api;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

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

import basura.Residuo;
import basura.ResiduoRegistro;
import puntos.PuntoRecoleccion;
import users.Usuario;

@Path("/registro")
public class RegistroAPI extends Api{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ResiduoRegistro> getRegistros(){
		Query get = this.em.createNamedQuery("getAllRegistros");
		List<ResiduoRegistro>resultado = get.getResultList();
		this.em.close();
		return resultado;
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResiduoRegistro getRegistro(@PathParam("id")int id) {
		ResiduoRegistro resultado = this.em.find(ResiduoRegistro.class, id);
		this.em.close();
		return resultado;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postRegistro(ResiduoRegistro registroNuevo) {
		Residuo residuoPersistido = this.em.find(Residuo.class, registroNuevo.getResiduo().getCodigo());
		Usuario userPersistido = this.em.find(Usuario.class, registroNuevo.getPersona().getId());
		PuntoRecoleccion puntoPersistido = this.em.find(PuntoRecoleccion.class, registroNuevo.getPuntorecoleccion().getId());
		System.out.println(residuoPersistido != null);
		System.out.println(userPersistido != null);
		System.out.println(puntoPersistido != null);
		if(residuoPersistido == null || userPersistido == null || puntoPersistido == null) {
			return Response.status(400).entity(registroNuevo.getResiduo().getCodigo()).build();
		}
		registroNuevo.setPersona(userPersistido);
		registroNuevo.setPuntorecoleccion(puntoPersistido);
		registroNuevo.setResiduo(residuoPersistido);
		if(registroNuevo.getFecha() == null) {
			registroNuevo.setFecha(new Date(Calendar.getInstance().getTimeInMillis()));
		}
		this.em.getTransaction().begin();
		this.em.persist(registroNuevo);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(registroNuevo).build();
	}
	@PUT
	public Response updateRegistros() {
		this.em.close();
		return Response.status(403).build();
	}
	
	@Path("/{id}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRegistro(@PathParam("id")int id, ResiduoRegistro registroNuevo) {
		ResiduoRegistro registroPersistido = this.em.find(ResiduoRegistro.class, id);
		Residuo residuoPersistido = this.em.find(Residuo.class, registroNuevo.getResiduo().getCodigo());
		Usuario userPersistido = this.em.find(Usuario.class, registroNuevo.getPersona().getId());
		PuntoRecoleccion puntoPersistido = this.em.find(PuntoRecoleccion.class, registroNuevo.getPuntorecoleccion().getId());
		if(registroPersistido == null | residuoPersistido == null || userPersistido == null || puntoPersistido == null) {
			return Response.status(400).build();
		}
		registroPersistido.copy(registroNuevo);
		this.em.getTransaction().begin();
		this.em.merge(registroPersistido);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(registroPersistido).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeRegistros() {
		try {
			this.em.getTransaction().begin();
			this.em.createNamedQuery("removeAllRegistros").executeUpdate();
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
	public Response removeRegistro(@PathParam("id") int id) {
		ResiduoRegistro borrar = this.em.find(ResiduoRegistro.class, id);
		if(borrar == null) {
			return Response.status(404).build();
		}
		try {
			this.em.getTransaction().begin();
			this.em.remove(borrar);
			this.em.getTransaction().commit();;			
		}
		catch (PersistenceException e) {
			this.em.close();
			return Response.status(409).build();
		}
		this.em.close();
		return Response.status(200).entity(borrar).build();
	}
}
