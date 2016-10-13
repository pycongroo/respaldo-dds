package acciones;

import javax.persistence.Entity;

import ar.edu.TPPOI.BusquedaHecha;
import ar.edu.TPPOI.Terminal;

@Entity
public class Almacenar extends Accion{

	public void ejecutarLuegoDeLaBusqueda(BusquedaHecha unaBusqueda, Terminal unaTerminal){
		
		unaTerminal.agregarBusquedaHecha(unaBusqueda);
	}
	
}
