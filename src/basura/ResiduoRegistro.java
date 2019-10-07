package basura;

import java.sql.Date;
import puntos.PuntoRecoleccion;
import users.Usuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ResiduoRegistro {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Residuo residuo;
	@Column
	private int cantidad;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private PuntoRecoleccion puntoRecolecion; 
	@Column
	private Date fecha;
	
	public ResiduoRegistro(Residuo residuo, int cantidad, Usuario usuario, PuntoRecoleccion puntoRecolecion,
			Date fecha) {
		super();
		this.residuo = residuo;
		this.cantidad = cantidad;
		this.usuario = usuario;
		this.puntoRecolecion = puntoRecolecion;
		this.fecha = fecha;
	}
	
	public ResiduoRegistro() {}

	public Residuo getResiduo() {
		return residuo;
	}

	public void setResiduo(Residuo residuo) {
		this.residuo = residuo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Usuario getPersona() {
		return usuario;
	}

	public void setPersona(Usuario persona) {
		this.usuario = persona;
	}

	public PuntoRecoleccion getPuntoRecolecion() {
		return puntoRecolecion;
	}

	public void setPuntoRecolecion(PuntoRecoleccion puntoRecolecion) {
		this.puntoRecolecion = puntoRecolecion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
