package puntos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Ubicacion {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private double x;
	@Column
	private double y;

	public Ubicacion(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public Ubicacion() {}
	
	public double getDistancia(Ubicacion otra) {
		double distanciaX = this.x - otra.getX();
		double distanciaY = this.y - otra.getY();
		distanciaX = Math.pow(distanciaX, 2);
		distanciaY = Math.pow(distanciaY, 2);
		double distancia  = distanciaX + distanciaY;
		distancia = Math.sqrt(distancia);
		return distancia;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public Ubicacion getUbicacionMasCercana(List<Ubicacion> ubicaciones) {
		Ubicacion ubicacionCercana = null;
		double mejorDistancia = -1;
		for(Ubicacion ubicacion: ubicaciones) {
			double distanciaActual = this.getDistancia(ubicacion);
			if(mejorDistancia == -1 ||  distanciaActual < mejorDistancia) {
				ubicacionCercana = ubicacion;
				mejorDistancia = distanciaActual;
			}
		}
		return ubicacionCercana;
		
	}
}
