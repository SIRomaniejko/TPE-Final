package basura;

import java.sql.Date;
import puntos.PuntoRecoleccion;
import users.Usuario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ResiduoRegistro {
	@Id
	@GeneratedValue
	private int id;
	@Column
	@ManyToOne
	private Residuo residuo;
	@Column
	private int cantidad;
	@Column
	@ManyToOne
	private Usuario persona;
	@Column
	@ManyToOne
	private PuntoRecoleccion puntoRecolecion; 
	@Column
	private Date fecha;
	
	public ResiduoRegistro(Residuo residuo, int cantidad, Usuario persona, PuntoRecoleccion puntoRecolecion,
			Date fecha) {
		super();
		this.residuo = residuo;
		this.cantidad = cantidad;
		this.persona = persona;
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
		return persona;
	}

	public void setPersona(Usuario persona) {
		this.persona = persona;
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
