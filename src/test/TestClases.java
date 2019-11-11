package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import puntos.Ubicacion;

public class TestClases {

	@Test
	public void test() {
		Ubicacion a =  new Ubicacion(1, 1);
		Ubicacion b = new Ubicacion(2, 2);
		System.out.println(a.getDistancia(b));
	}
	
	@Test
	public void test2() {
		Ubicacion mainUbicacion = new Ubicacion(1, 1);
		Ubicacion ubicacionRegreso = new Ubicacion(2,2);
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(new Ubicacion(100, 100));
		ubicaciones.add(new Ubicacion(50, 50));
		ubicaciones.add(new Ubicacion(25, 25));
		ubicaciones.add(new Ubicacion(12, 12));
		ubicaciones.add(ubicacionRegreso);
		ubicaciones.add(new Ubicacion(7, 12));
		ubicaciones.add(new Ubicacion(7, 2));
		assertEquals(ubicacionRegreso, mainUbicacion.getUbicacionMasCercana(ubicaciones));
	}

}
