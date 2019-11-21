package basura;

import java.sql.Date;
import puntos.PuntoRecoleccion;
import users.Usuario;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name= "getBasuraUsuario", query="SELECT rr FROM ResiduoRegistro rr WHERE rr.usuario = ?1"),
	@NamedQuery(name="getRecicladosUsuario", query="SELECT rr FROM ResiduoRegistro rr WHERE rr.usuario = ?1 AND rr.fecha BETWEEN ?2 AND ?3 "),
	@NamedQuery(name="getRecicladoLugar", query="SELECT rr FROM ResiduoRegistro rr WHERE rr.puntoRecoleccion = ?1 AND rr.fecha BETWEEN ?2 AND ?3 "),
	@NamedQuery(name="getHistorialPunto", query="SELECT rr FROM ResiduoRegistro rr WHERE rr.fueRecolectado = TRUE AND rr.puntoRecoleccion = ?1"),
	@NamedQuery(name="getAllRegistros", query="SELECT rr FROM ResiduoRegistro rr"),
	@NamedQuery(name="removeAllRegistros", query="DELETE FROM ResiduoRegistro")
})


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
	private PuntoRecoleccion puntoRecoleccion; 
	@Column
	private Date fecha;
	@Column
	private boolean fueRecolectado;
	@Column 
	private double volumenTotal;
	
	public ResiduoRegistro(Residuo residuo, int cantidad, Usuario usuario, PuntoRecoleccion puntoRecoleccion,Date fecha) {
		this.residuo = residuo;
		this.cantidad = cantidad;
		this.usuario = usuario;
		this.puntoRecoleccion = puntoRecoleccion;
		this.fecha = fecha;
		this.fueRecolectado = false;
		this.calcularVolumen();
	}
	
	public ResiduoRegistro() {
	}
	
	public void copy(ResiduoRegistro copiar) {
		
		this.fueRecolectado = copiar.isFueRecolectado();
	}

	public Residuo getResiduo() {
		return this.residuo;
	}

	public void setResiduo(Residuo residuo) {
		this.residuo = residuo;
	}

	public int getCantidad() {
		return this.cantidad;
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

	public PuntoRecoleccion getPuntorecoleccion() {
		return puntoRecoleccion;
	}

	public void setPuntorecoleccion(PuntoRecoleccion puntoRecoleccion) {
		this.puntoRecoleccion = puntoRecoleccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public void calcularVolumen() {
		this.volumenTotal = this.cantidad*this.residuo.getVolumen();
	}
	
	public double getVolumen() {
		return this.volumenTotal;
	}
	public boolean isFueRecolectado() {
		return fueRecolectado;
	}
	
	
	public void setFueRecolectado(boolean fueRecolectado) {
		this.fueRecolectado = fueRecolectado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
