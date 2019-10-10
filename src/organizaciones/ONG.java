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
import javax.persistence.OneToMany;

@Entity 
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
	}

	public ONG() {
	}
	
	public boolean noEsVacio() {
		return this.residuosONG.size() != 0;
	}
	
}
