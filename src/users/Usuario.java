package users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tabla_usuario")
public class Usuario extends Individuo{
	private int dni;
	@Column
	private String apellido;
	@Column
	private String domicilio;
	
	public Usuario(String nombre, String apellido, int dni, String domicilio, double longitud, double latitud) {
		super(nombre,latitud,longitud);
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
	}
	public Usuario() {
		
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

}
