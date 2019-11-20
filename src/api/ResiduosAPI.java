package api;

import basura.Residuo;
import organizaciones.ONG;

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
		Residuo residuoPersistir = new Residuo(residuoNuevo.getNombre(), residuoNuevo.getVolumen(), residuoNuevo.getValor(), residuoNuevo.isEsReciclable());
		ONG ongPersistir = em.find(ONG.class, residuoNuevo.getOngPertenece().getId());
		if(ongPersistir == null) {
			ongPersistir = new ONG(residuoNuevo.getOngPertenece().getNombre());
			ongPersistir.addResiduos(residuoPersistir);
			em.persist(ongPersistir);
		}
		else {
			ongPersistir.addResiduos(residuoPersistir);
			em.merge(ongPersistir);
		}
		this.em.persist(residuoPersistir);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(residuoNuevo).build();
	}
//	Residuo residuoDefault2 = new Residuo("tapita", 0.1, 5, true);
//	ONG ongdefault1 = em.find(ONG.class, 2);
//	ongdefault1.addResiduos(residuoDefault2);
//	this.em.getTransaction().begin();
//	this.em.persist(residuoDefault2);
//	this.em.merge(ongdefault1);
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
