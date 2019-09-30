package users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_individuo")
public class Individuo{
	
	@Id
	private String identificador;
	@Column
	private String nombre;

	public Individuo(String id, String nombre, double latitud, double longitud) {
		this.identificador = id;
		this.nombre = nombre;
	}
	
	public Individuo() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
