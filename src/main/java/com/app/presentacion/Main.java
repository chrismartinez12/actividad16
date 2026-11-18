package com.app.presentacion;

import com.app.logica.Artista;
import com.app.logica.Cancion;
import com.app.logica.ControladoraLogica;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
	public static void main(String[] args) {
	
		
		Calendar cal1 = new GregorianCalendar(1972, Calendar.AUGUST, 9);
        Date fechaJuanes = cal1.getTime();
        
        Calendar cal2 = new GregorianCalendar(2009, Calendar.DECEMBER, 11);
        Date fechaChepe = cal2.getTime();
                
		ControladoraLogica conLog = new ControladoraLogica();
		try {
		Artista art1 = new Artista("juan", "Kpop", "xd", 23, fechaJuanes);
		conLog.crearArtista(art1);

		Artista art2 = new Artista("miguel", "Kasdaspop", "xasdasd",22,fechaChepe);
		conLog.crearArtista(art2);
		

		Cancion mus1 = new Cancion("canusa bgra",2.2f,2020);
		conLog.crearCancion(mus1);

		Cancion mus2 = new Cancion("asda",1.2f,1996);
		conLog.crearCancion(mus2);

		
		} catch (Exception e) {
			System.out.println("No se pudo crear el elemento: " + e.getMessage());
		}
		// ----- LECTURA DE ARTISTAS -----
		System.out.println("=== TODOS LOS ARTISTAS ===");
		ControladoraLogica control = new ControladoraLogica();
		List<Artista> artistas = control.listarArtistas();
		for (Artista a : artistas) {
		System.out.println(a);
		System.out.println("===============================");	
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 1 ===");
		Artista encontrado = control.buscarUnArtista(1);
		if (encontrado != null) {
		System.out.println(encontrado);
		} else {
		System.out.println("No existe un artista con ese id");
		}
		System.out.println("=== BUSCAR ARTISTA CON ID 999 ===");
		Artista noExiste = control.buscarUnArtista(999);
		if (noExiste != null) {
		System.out.println(noExiste);
		} else {
		System.out.println("No existe un artista con ese id");
		
		// ----- LECTURA DE CANCIONES -----
	System.out.println("=== TODAS LAS CANCIONES ===");
    List<Cancion> Canciones = control.listarCancion();
    for (Cancion c : Canciones) {
	System.out.println(c);
	System.out.println("===============================");	
	}
	System.out.println("=== BUSCAR Cancion CON ID 1 ===");
	Cancion encontradoCancion = control.buscarUnaCancion(1);
	if (encontradoCancion != null) {
	System.out.println(encontradoCancion);
	} else {
	System.out.println("No existe esa Cancion con ese id");
	}
	System.out.println("=== BUSCAR Cancion CON ID 999 ===");
	Cancion noExisteCancion = control.buscarUnaCancion(999);
	if (noExisteCancion != null) {
	System.out.println(noExisteCancion);
	} else {
	System.out.println("No existe esa Cancion con ese id");

	}
}}}


