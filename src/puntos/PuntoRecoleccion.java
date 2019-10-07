package puntos;

import java.util.List;

import basura.ResiduoRegistro;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name="isFull", query="SELECT 1 FROM PuntoRecoleccion p WHERE p.volumenParaRecolectar <= (SELECT SUM(r.volumen_total) FROM ResiduoRegistro r WHERE  p.id_recoleccion = r.id_recoleccion )AND p.id = ?1")
})
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
