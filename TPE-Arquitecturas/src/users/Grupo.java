package users;

import java.util.List;

public class Grupo extends Individuo{
	private String tipoGrupo;
	private List<Individuo> individuos;
	public Grupo(String tipoGrupo, String nombre, List<Individuo> individuos) {
		super(nombre);
		this.tipoGrupo = tipoGrupo;
		this.individuos = individuos;
	}
	public String getTipoGrupo() {
		return tipoGrupo;
	}
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	public List<Individuo> getIndividuos() {
		return individuos;
	}
	public void setIndividuos(List<Individuo> individuos) {
		this.individuos = individuos;
	}
}
