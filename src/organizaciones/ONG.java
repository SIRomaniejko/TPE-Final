package organizaciones;

import java.util.List;

import basura.Residuo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity 
public class ONG {
	@Id
	@GeneratedValue
	private int id;
	@ManyToMany
	private List<Residuo> residuos;

	public ONG(List<Residuo> residuos) {
		this.residuos = residuos;
	}

	public ONG() {}
	
	public List<Residuo> getResiduos() {
		return residuos;
	}

	public void setResiduos(List<Residuo> residuos) {
		this.residuos = residuos;
	}	
}
