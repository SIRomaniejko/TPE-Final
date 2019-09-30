package users;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class Grupo extends Individuo{
	@Column
	private String tipoGrupo;
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Individuo> individuos;
	
	public Grupo(String tipoGrupo, String nombre, List<Individuo> individuos, double longitud, double latitud) {
		super(nombre,latitud,longitud);
		this.tipoGrupo = tipoGrupo;
		this.individuos = individuos;
	}
	public Grupo() {
		
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
