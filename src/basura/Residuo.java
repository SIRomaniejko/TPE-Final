package basura;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="isRecyclable", query="SELECT r.esReciclable FROM Residuo r WHERE r.codigo = ?1 ")
})
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
	
	public Residuo(String nombre, double volumen, double valor, boolean esReciclable) {
		this.nombre = nombre;
		this.volumen = volumen;
		this.valor = valor;
		this.esReciclable = esReciclable;
	}

	public Residuo() {}
	
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
