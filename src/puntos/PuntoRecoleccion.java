package puntos;

import java.util.ArrayList;
import java.util.List;

import basura.ResiduoRegistro;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
		@NamedQuery(name="isFull", query="SELECT 1 FROM PuntoRecoleccion p WHERE p.volumenParaRecolectar <= (SELECT SUM(rr.volumenTotal) FROM ResiduoRegistro rr WHERE  p = rr.puntoRecoleccion )AND p.id = ?1"),
		@NamedQuery(name="getAllPuntos", query="SELECT c FROM PuntoRecoleccion c"),
		@NamedQuery(name="deleteAllPuntosRecoleccion", query="DELETE FROM PuntoRecoleccion")
})
public class PuntoRecoleccion {
	@Id
	@GeneratedValue
	private int id;
	@OneToMany(mappedBy = "puntoRecoleccion")
	private List<ResiduoRegistro> residuos;
	@Column
	private double volumenParaRecolectar;
	@OneToOne(cascade=CascadeType.ALL)
	private Ubicacion ubicacion;
	
	public PuntoRecoleccion(double volumenParaRecolectar, double latitud, double longitud) {
		this.residuos = new ArrayList<ResiduoRegistro>();
		this.volumenParaRecolectar = volumenParaRecolectar;
		this.ubicacion = new Ubicacion(latitud,longitud);
	}
	
	public PuntoRecoleccion() {}
	
	@JsonIgnore
	public List<ResiduoRegistro> getResiduos() {
		return residuos;
	}
	public void setResiduos(List<ResiduoRegistro> residuos) {
		this.residuos = residuos;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void addResiduo(ResiduoRegistro residuo) {
		this.residuos.add(residuo);
	}
	public double getVolumenParaRecolectar() {
		return volumenParaRecolectar;
	}
	public void setVolumenParaRecolectar(double volumenParaRecolectar) {
		this.volumenParaRecolectar = volumenParaRecolectar;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public void copy(PuntoRecoleccion copiar) {
		this.volumenParaRecolectar = copiar.getVolumenParaRecolectar();
		this.ubicacion = copiar.getUbicacion();
	}
}
