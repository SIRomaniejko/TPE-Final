package api;

import basura.Residuo;
import users.Usuario;

import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/rest")
public class Api {
	private EntityManager em;

	public Api(){
		this.em = EMF.createEntityManager(); //EMF se encarga de que haya un solo entity manager factory
//		List<Usuario> usuarios = new ArrayList<>();
//		String csvFile = "C:\\Users\\tutip\\Documents\\0000TUDAI\\Proyectos Java\\Arquitecturas Web\\TPE-Final\\src\\input\\usuario.csv";
//		String line;
//		String csvSplitBy = ",";
//		Usuario aux;
//
//		try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
//			while((line = br.readLine()) != null) {
//				String[] items = line.split(csvSplitBy);
//				aux = new Usuario(items[0], items[1], Integer.parseInt(items[2]), items[3], Double.parseDouble(items[4]), Double.parseDouble(items[5]));
//				usuarios.add(aux);
//			}
//			System.out.println("Cargado Usuarios con exito");
//		}catch(IOException e){
//			e.printStackTrace();
//		}
//		this.em.getTransaction().begin();
//		for (Usuario usuario: usuarios) {
//			this.em.persist(usuario);
//		}
	}

	@Path("/residuos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Residuo> getResiduos(){
		Query query = this.em.createNamedQuery("getAllResiduos");
		List<Residuo> result = query.getResultList();
		System.out.println(result);
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

	@Path("/usuarios")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuario(){
		
		em.getTransaction().begin();
		Usuario personaDefault1 = new Usuario("jose", "olmedo", 1111, "domicilio", 1, 1);
		this.em.persist(personaDefault1);
		this.em.getTransaction().commit();
		System.out.println("Persistido Usuarios con exito");
		
		Query query = this.em.createNamedQuery("getAll");
		List<Usuario> result = query.getResultList();
		this.em.close();
		return result;
	}

	@Path("/usuario/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario getUsuario(@PathParam("id") int id){
		Query query = this.em.createNamedQuery("getByDni");
		query.setParameter(1,id);
		Usuario result;
		try{
			result = (Usuario) query.getSingleResult();
		}catch (NoResultException e){
			System.out.println(e);
			result = null;
		}
		return result;
	}
}
