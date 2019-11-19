package users;

import java.util.List;

import javax.persistence.*;

import puntos.Ubicacion;

@Entity
@Table(name="table_individuo")
public abstract class Individuo{
	
	@Id
	private String identificador;
	@Column
	private String nombre;
	@OneToOne(cascade=CascadeType.ALL)
	private Ubicacion ubicacion;

	public Individuo(String id, String nombre, double latitud, double longitud) {
		this.identificador = id;
		this.nombre = nombre;
		ubicacion = new Ubicacion(latitud,longitud);
	}
	
	public Individuo() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUbicacion(Ubicacion u){
		this.ubicacion = u;
	}

	public Ubicacion getUbicacion(){
		return this.ubicacion;
	}
	public String getId() {
		return this.identificador;
	}
	public void setId(String identificador) {
		this.identificador = identificador;
	}
	public abstract List<Usuario> getUsuarios();
	
	
}
