package basura;

public class Residuo {
	private String nombre;
	private int codigo;
	private double volumen;
	private double valor;
	private boolean esReciclable;
	
	public Residuo(String nombre, int codigo, double volumen, double valor, boolean esReciclable) {
		this.nombre = nombre;
		this.codigo = codigo;
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

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
