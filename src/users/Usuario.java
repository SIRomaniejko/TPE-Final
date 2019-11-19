package users;

import puntos.Ubicacion;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NamedQueries({
	@NamedQuery(name="getByName", query="SELECT u FROM Usuario u WHERE u.nombre = ?1"),
	@NamedQuery(name="getAllUsers", query="SELECT u FROM Usuario u"),
	@NamedQuery(name="getNombre", query="SELECT u.nombre FROM Usuario u WHERE u.dni = ?1"),
	@NamedQuery(name="deleteAllUsers", query="DELETE FROM Usuario"),
	@NamedQuery(name="getByDni", query="SELECT u FROM Usuario u WHERE u.dni = ?1"),
	@NamedQuery(name="getAhorroUsuarioTotal", query="SELECT SUM(rr.cantidad) * MAX(r.valor) FROM ResiduoRegistro rr JOIN rr.residuo r WHERE rr.usuario = ?1 GROUP BY rr.residuo"),
	@NamedQuery(name="getAhorroUsuario", query="SELECT SUM(rr.cantidad) * MAX(r.valor), r.nombre FROM ResiduoRegistro rr JOIN rr.residuo r WHERE rr.usuario = ?1 GROUP BY rr.residuo, r.nombre"),
	@NamedQuery(name="getAhorroAONG", query="SELECT SUM(rr.cantidad) * MAX(r.valor), o.nombre FROM ResiduoRegistro rr JOIN rr.residuo r JOIN r.ongPertenece o WHERE rr.usuario = ?1 GROUP BY o.nombre, r")
}) 
public class Usuario extends Individuo{
	@Column
	private int dni;
	@Column
	private String apellido;
	
	public Usuario(String nombre, String apellido, int dni, double latitud, double longitud) {
		super(((Integer)dni).toString(), nombre,latitud,longitud);
		this.apellido = apellido;
		this.dni = dni;
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
	public String toString() {
		return super.getNombre() + "; " + this.apellido;
	}
	
	public void copyUser(Usuario userCopy) {
		this.setDni(userCopy.getDni());
		this.setApellido(userCopy.getApellido());
		this.setNombre(userCopy.getNombre());
		this.setUbicacion(userCopy.getUbicacion()); 
		this.setId(userCopy.getId());
	}
	
	@JsonIgnore
	public List<Usuario> getUsuarios() {
		ArrayList<Usuario> regreso = new ArrayList<Usuario>();
		regreso.add(this);
		return regreso;
	}
}
