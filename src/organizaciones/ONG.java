package organizaciones;

import java.util.List;

import basura.Residuo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity 
public class ONG {
	@Column
	@ManyToMany
	private List<Residuo> residuos;

	public ONG(List<Residuo> residuos) {
		this.residuos = residuos;
	}

	public List<Residuo> getResiduos() {
		return residuos;
	}

	public void setResiduos(List<Residuo> residuos) {
		this.residuos = residuos;
	}
	
	
}
