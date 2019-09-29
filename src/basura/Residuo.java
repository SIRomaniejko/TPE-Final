package basura;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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
