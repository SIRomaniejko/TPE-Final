package abstracts;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public abstract class Coordenada {
	@Column
	private double latitud;
	@Column
	private double longitud;
	
	public Coordenada(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
}
