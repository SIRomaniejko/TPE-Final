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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQueries({
	@NamedQuery(name="getAllONG", query="SELECT o FROM ONG o"),
	@NamedQuery(name="deleteAllONG", query="DELETE FROM ONG")
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
		if(!this.residuosONG.contains(newResiduo)) {
			newResiduo.setOngPertenece(this);
			this.residuosONG.add(newResiduo);			
		}
	}
	
	public String getNombre(){
		return this.nombre;
	}
	public int getId() {
		return this.id;
	}
	
	public void copyONG(ONG ongCopiar) {
		this.nombre = ongCopiar.getNombre();
	}
	
	@JsonIgnore
	public List<Residuo> getResiduos(){
		return this.residuosONG;
	}
}
