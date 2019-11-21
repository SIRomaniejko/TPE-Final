package users;

import java.util.List;

import javax.persistence.*;

import puntos.Ubicacion;

@Entity
@Table(name="table_individuo")
public abstract class Individuo{
	
	@GeneratedValue
	@Id
	private int id;
	@Column
	private String nombre;
	@OneToOne(cascade=CascadeType.ALL)
	private Ubicacion ubicacion;

	public Individuo(String nombre, double latitud, double longitud) {
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
	public int getId() {
		return this.id;
	}
	public abstract List<Usuario> getUsuarios();
	
	
}
