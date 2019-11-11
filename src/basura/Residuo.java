package basura;

import javax.persistence.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import organizaciones.ONG;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllResiduos", query="SELECT r FROM Residuo r"),
	@NamedQuery(name="getResiduo", query="SELECT r FROM Residuo r WHERE r.codigo = ?1"),
	@NamedQuery(name="isRecyclable", query="SELECT r.esReciclable FROM Residuo r WHERE r.codigo = ?1 ")
})
@Path("/residuo")
public class Residuo {
	@Id
	@GeneratedValue
	private int codigo;
	@Column
	private String nombre;
	@Column
	private double volumen;
	@Column
	private double valor;
	@Column
	private boolean esReciclable;
	@ManyToOne
	@JoinColumn
	private ONG ongPertenece;
	
	public ONG getOngPertenece() {
		return ongPertenece;
	}

	public void setOngPertenece(ONG ongPertenece) {
		this.ongPertenece = ongPertenece;
	}

	public Residuo(String nombre, double volumen, double valor, boolean esReciclable) {
		this.nombre = nombre;
		this.volumen = volumen;
		this.valor = valor;
		this.esReciclable = esReciclable;
	}

	public Residuo() {}

	@Path("/nombre")
	@GET
	@Produces("application/json")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public boolean isEsReciclable() {
		return esReciclable;
	}

	public void setEsReciclable(boolean esReciclable) {
		this.esReciclable = esReciclable;
	}
	
	
	
	

}
