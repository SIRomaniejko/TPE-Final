package api;

import basura.Residuo;
import basura.ResiduoRegistro;
import organizaciones.ONG;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/residuo")
public class ResiduosAPI extends Api{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Residuo> getAllResiduos(){
		Query busqueda = this.em.createNamedQuery("getAllResiduos");
		List<Residuo> resultado = null;
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
	public Residuo getResiduo(@PathParam("id") int id) {
		Query busqueda = this.em.createNamedQuery("getResiduo", Residuo.class);
		busqueda.setParameter(1, id);
		Residuo resultado = (Residuo)busqueda.getSingleResult();
		this.em.close();
		return resultado;
	}

	
	@PUT
	public Response updateResiduos() {
		this.em.close();
		return Response.status(403).build();
	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateResiduos(@PathParam("id") int id, Residuo residuoNuevo) {
		Residuo residuoPersistido = em.find(Residuo.class, id);
		ONG ongPersistida = em.find(ONG.class, residuoNuevo.getOngPertenece().getId());
		if(residuoPersistido == null || ongPersistida == null) {
			return Response.status(400).build();
		}
		residuoPersistido.copy(residuoNuevo);
		ongPersistida.addResiduos(residuoPersistido);
		this.em.getTransaction().begin();
		em.merge(ongPersistida);
		this.em.merge(residuoPersistido);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(residuoPersistido).build();
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response postResiduo(Residuo residuoNuevo) {
		this.em.getTransaction().begin();
		Residuo residuoPersistir = new Residuo(residuoNuevo.getNombre(), residuoNuevo.getVolumen(), residuoNuevo.getValor(), residuoNuevo.isEsReciclable());
		ONG ongPersistir = em.find(ONG.class, residuoNuevo.getOngPertenece().getId());
		if(ongPersistir == null) {
			return Response.status(400).build();
		}
		ongPersistir.addResiduos(residuoPersistir);
		em.merge(ongPersistir);
		this.em.persist(residuoPersistir);
		this.em.getTransaction().commit();
		this.em.close();
		return Response.status(200).entity(residuoPersistir).build();
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteResiduos() {
		try {
			this.em.getTransaction().begin();
			Query remover = this.em.createNamedQuery("deleteAllResiduos");
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
		
		Residuo a = this.em.find(Residuo.class, id);
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
