package api;

import basura.Residuo;

import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/rest")
public class Api {
	private EntityManagerFactory emf;
	private EntityManager em;

	public Api(){
		this.emf = Persistence.createEntityManagerFactory("my_persistence_unit");
		this.em = emf.createEntityManager();
	}

	@Path("/residuos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Residuo> getResiduos(){
		Query query = this.em.createNamedQuery("getAllResiduos");
		List<Residuo> result = query.getResultList();
		return result;
	}

	@Path("/residuo/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Residuo getResiduo(@PathParam("id") int id){
		Query query = this.em.createNamedQuery("getResiduo");
		query.setParameter(1,id);
		Residuo result;
		try{
			result = (Residuo) query.getSingleResult();
		}catch (NoResultException e){
			System.out.println(e);
			result = null;
		}
		return result;
	}
}
