package clasesParaTests;

import procesos.ProcActualizarLocalesComerciales;

public class ProcesoActualizadorLocalesImpostor extends ProcActualizarLocalesComerciales {
	
	public void ejecutar(){
		procesarLinea("Starbucks;cafe te muffin");
		procesarLinea("musimundo;musica");
		procesarLinea("cine Abasto;pochoclos peliculas entradas cine");
		procesarLinea("SportClub;aparatos");
	}
	
}
