package basura;

import java.util.Date;

import puntos.PuntoRecoleccion;
import users.Usuario;

public class ResiduoRegistro {
	private Residuo residuo;
	private int cantidad;
	private Usuario persona;
	private PuntoRecoleccion puntoRecolecion; 
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
