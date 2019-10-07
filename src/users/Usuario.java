package users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@NamedQueries({
	@NamedQuery(name="getByName", query="SELECT u FROM Usuario u WHERE u.nombre = ?1"),
	@NamedQuery(name="getAll", query="SELECT u FROM Usuario u"),
	@NamedQuery(name="getByDni", query="SELECT u FROM Usuario u WHERE u.dni = ?1")
}) 
public class Usuario extends Individuo{
	@Column
	private int dni;
	@Column
	private String apellido;
	@Column
	private String domicilio;
	
	public Usuario(String nombre, String apellido, int dni, String domicilio, double longitud, double latitud) {
		super(((Integer)dni).toString(), nombre,latitud,longitud);
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
	public String toString() {
		return super.getNombre() + "; " + this.apellido;
	}
}
