package puntos;

import java.util.List;

import basura.ResiduoRegistro;

public class PuntoRecoleccionHs extends PuntoRecoleccion{
	private int horarioInicio;
	private int horarioCierre;
	public PuntoRecoleccionHs(List<ResiduoRegistro> residuos, double volumenParaRecolectar, int horarioInicio,
			int horarioCierre) {
		super(residuos, volumenParaRecolectar);
		this.horarioInicio = horarioInicio;
		this.horarioCierre = horarioCierre;
	}
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
