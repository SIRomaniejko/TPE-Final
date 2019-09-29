package users;

public class Usuario extends Individuo{

	private String apellido;
	private int dni;
	private String domicilio;
	
	public Usuario(String nombre, String apellido, int dni, String domicilio, double longitud, double latitud) {
		super(nombre,latitud,longitud);
		this.apellido = apellido;
		this.dni = dni;
		this.domicilio = domicilio;
	}

	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

}
