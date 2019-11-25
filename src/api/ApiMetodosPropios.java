package api;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import basura.Residuo;
import basura.ResiduoRegistro;
import organizaciones.ONG;
import users.Usuario;
@Path("metodosAdmin")
public class ApiMetodosPropios extends Api{
	@POST
	@Path("/refreshMicro")
	@Produces(MediaType.APPLICATION_JSON)
	public Response refresh() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8081");
		
		
		String[] paginas = {"registro", "usuario", "residuo"};
		WebTarget tarjetLocal;
		Invocation.Builder invocationBuilder;
		
		//borramos
		for(String pagina: paginas) {
			tarjetLocal = target.path(pagina);
			invocationBuilder =  tarjetLocal.request(MediaType.APPLICATION_JSON);
			try {
				Response a = invocationBuilder.delete();
				
			}
			catch(ProcessingException exc) {
				return Response.status(503).build();
			}
			
		}
		
		//creamos
		Response resp;
		
		//enviamos usuarios
		List<Usuario> users = this.em.createNamedQuery("getAllUsers").getResultList();
		tarjetLocal = target.path("usuario");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		
		try {
			resp = invocationBuilder.post(Entity.entity(users, MediaType.APPLICATION_JSON));
			System.out.println("carga usuarios: " + resp.getStatus());
		}
		catch(ProcessingException exc) {
			resp = Response.status(404).build();
		}
		if(resp.getStatus() == 404) {
			return Response.status(503).build();
		}
		
		//enviamos Residuos
		List<Residuo> residuos = this.em.createNamedQuery("getAllResiduos").getResultList();
		tarjetLocal = target.path("residuo");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		try {
			Residuo a = residuos.get(0);
			resp = invocationBuilder.post(Entity.entity(residuos, MediaType.APPLICATION_JSON));
			System.out.println("carga residuos: " + resp.getStatus());
		}
		catch(ProcessingException exc) {
			resp = Response.status(404).build();
		}
		if(resp.getStatus() == 404) {
			return Response.status(503).build();
		}
		
		//enviamos registros
		List<ResiduoRegistro> residuoRegistro = this.em.createNamedQuery("getAllRegistros").getResultList();
		tarjetLocal = target.path("registro");
		invocationBuilder = tarjetLocal.request(MediaType.APPLICATION_JSON);
		try {
			resp = invocationBuilder.post(Entity.entity(residuoRegistro, MediaType.APPLICATION_JSON));
			System.out.println("carga registros: " + resp.getStatus());
		}
		catch(ProcessingException exc) {
			resp = Response.status(404).build();
		}
		if(resp.getStatus() == 404) {
			return Response.status(503).build();
		}
		return Response.status(200).build();
	}
}
