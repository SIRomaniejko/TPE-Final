package organizaciones;

import java.util.List;

import basura.Residuo;

public class ONG {
	private List<Residuo> residuos;

	public ONG(List<Residuo> residuos) {
		super();
		this.residuos = residuos;
	}

	public List<Residuo> getResiduos() {
		return residuos;
	}

	public void setResiduos(List<Residuo> residuos) {
		this.residuos = residuos;
	}
	
	
}
