package api;

import basura.Residuo;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/basura")
public class ResiduosAPI extends Api{
	@Path("/residuo")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Residuo> getAllResiduos(){
		Query busqueda = this.em.createNamedQuery("getAllResiduos");
		try {
			List<Residuo> resultado = busqueda.getResultList();
			this.em.close();
			return resultado;
		}
		catch(NoResultException exc) {
			this.em.close();
			return null;
		}

	}
	@Path("/residuo/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Residuo getResiduo(@PathParam("id") int id) {
		Query busqueda = this.em.createNamedQuery("getResiduo", Residuo.class);
		busqueda.setParameter(1, id);
		Residuo resultado = (Residuo)busqueda.getSingleResult();
		this.em.close();
		return resultado;
	}

	@Path("/residuo")
	@PUT
	public Response updateResiduos() {
		this.em.close();
		return Response.status(403).build();
	}

	@Path("/residuo/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateResiduos(@PathParam("id") int id, Residuo residuoNuevo) {
		this.em.close();
		return Response.status(403).build();
	}

	@Path("/residuo")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postResiduo(Residuo residuoNuevo) {
		this.em.getTransaction().begin();
		em.persist(residuoNuevo);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(residuoNuevo).build();
	}

	@Path("/residuo")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteResiduos() {
		this.em.close();
		return Response.status(403).build();
	}

	@Path("/residuo/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteResiduo(@PathParam("id") String id) {

		Residuo a = this.em.find(Residuo.class, id);
		this.em.getTransaction().begin();
		this.em.remove(a);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(a).build();
	}
}
