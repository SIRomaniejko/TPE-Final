package users;

import abstracts.Coordenada;

public class Individuo extends Coordenada{
	private String nombre;

	public Individuo(String nombre, double latitud, double longitud) {
		super(latitud, longitud);
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
