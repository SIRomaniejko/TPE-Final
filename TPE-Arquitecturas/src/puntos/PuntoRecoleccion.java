package puntos;

import java.util.List;

import basura.ResiduoRegistro;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PuntoRecoleccion {
	@Column
	private List<ResiduoRegistro> residuos;
	@Column
	private double volumenParaRecolectar;
	
	public PuntoRecoleccion(List<ResiduoRegistro> residuos, double volumenParaRecolectar) {
		this.residuos = residuos;
		this.volumenParaRecolectar = volumenParaRecolectar;
	}
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
