package puntos;

import java.util.List;

import basura.ResiduoRegistro;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PuntoRecoleccionHs extends PuntoRecoleccion{
	@Column
	private int horarioInicio;
	@Column
	private int horarioCierre;
	
	public PuntoRecoleccionHs(List<ResiduoRegistro> residuos, double volumenParaRecolectar, int horarioInicio,
			int horarioCierre, double latitud, double longitud) {
		super(volumenParaRecolectar, latitud, longitud);
		this.horarioInicio = horarioInicio;
		this.horarioCierre = horarioCierre;
	}
	
	public PuntoRecoleccionHs() {}
	
	public int getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(int horarioInicio) {
		this.horarioInicio = horarioInicio;
	}
	public int getHorarioCierre() {
		return horarioCierre;
	}
	public void setHorarioCierre(int horarioCierre) {
		this.horarioCierre = horarioCierre;
	}	
}
