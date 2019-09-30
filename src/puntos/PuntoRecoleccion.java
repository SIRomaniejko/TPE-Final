package puntos;

import java.util.List;

import basura.ResiduoRegistro;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PuntoRecoleccion {
	@Id
	@GeneratedValue
	private int id;
	@OneToMany
	private List<ResiduoRegistro> residuos;
	@Column
	private double volumenParaRecolectar;
	
	public PuntoRecoleccion(List<ResiduoRegistro> residuos, double volumenParaRecolectar) {
		this.residuos = residuos;
		this.volumenParaRecolectar = volumenParaRecolectar;
	}
	
	public PuntoRecoleccion() {}
	
	public List<ResiduoRegistro> getResiduos() {
		return residuos;
	}
	public void setResiduos(List<ResiduoRegistro> residuos) {
		this.residuos = residuos;
	}
	public double getVolumenParaRecolectar() {
		return volumenParaRecolectar;
	}
	public void setVolumenParaRecolectar(double volumenParaRecolectar) {
		this.volumenParaRecolectar = volumenParaRecolectar;
	}
}
