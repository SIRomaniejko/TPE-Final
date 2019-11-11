package organizaciones;

import java.util.ArrayList;
import java.util.List;

import basura.Residuo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	
})
public class ONG {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String nombre;
	@OneToMany(mappedBy="ongPertenece")
	private List<Residuo> residuosONG;

	public ONG(String nombre) {
		this.nombre = nombre;
		residuosONG = new ArrayList<Residuo>();
	}

	public ONG() {
	}
	
	public boolean noEsVacio() {
		return this.residuosONG.size() != 0;
	}
	
	public void addResiduos(Residuo newResiduo) {
		newResiduo.setOngPertenece(this);
		this.residuosONG.add(newResiduo);
	}

	public String getNombre(){
		return this.nombre;
	}

	public List<Residuo> getResiduos(){
		return this.residuosONG;
	}
}
