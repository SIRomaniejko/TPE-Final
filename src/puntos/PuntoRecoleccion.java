package puntos;

import java.util.ArrayList;
import java.util.List;

import basura.ResiduoRegistro;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name="isFull", query="SELECT 1 FROM PuntoRecoleccion p WHERE p.volumenParaRecolectar <= (SELECT SUM(rr.volumenTotal) FROM ResiduoRegistro rr WHERE  p = rr.puntoRecoleccion )AND p.id = ?1"),
		@NamedQuery(name="getAllPuntos", query="SELECT c FROM PuntoRecoleccion c")
})
public class PuntoRecoleccion {
	@Id
	@GeneratedValue
	private int id;
	@OneToMany(mappedBy = "puntoRecoleccion")
	private List<ResiduoRegistro> residuos;
	@Column
	private double volumenParaRecolectar;
	
	public PuntoRecoleccion(double volumenParaRecolectar) {
		this.residuos = new ArrayList<ResiduoRegistro>();
		this.volumenParaRecolectar = volumenParaRecolectar;
	}
	
	public PuntoRecoleccion() {}
	
	public List<ResiduoRegistro> getResiduos() {
		return residuos;
	}
	public void setResiduos(List<ResiduoRegistro> residuos) {
		this.residuos = residuos;
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
}
