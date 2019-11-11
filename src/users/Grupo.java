package users;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
@Entity
public class Grupo extends Individuo{
	@Column
	private String tipoGrupo;
	@OneToMany(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Individuo> individuos;
	
	public Grupo(String tipoGrupo, String nombre, List<Individuo> individuos, double longitud, double latitud) {
		super(nombre,nombre,latitud,longitud);
		this.tipoGrupo = tipoGrupo;
		this.individuos = new ArrayList<Individuo>();
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
	public void addIndividuo(Individuo newIndividuo) {
		this.individuos.add(newIndividuo);
	}
	
	@Override
	public List<Usuario> getUsuarios() {
		ArrayList<Usuario>usuarios = new ArrayList<Usuario>();
		for(Individuo miembro: individuos) {
			List<Usuario> miembros = miembro.getUsuarios();
			usuarios.addAll(miembros);
		}
		return usuarios;
	}
	
}
