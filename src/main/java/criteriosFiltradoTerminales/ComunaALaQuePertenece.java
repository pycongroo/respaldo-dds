package criteriosFiltradoTerminales;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.TPPOI.Terminal;
import deApoyo.Comuna;

public class ComunaALaQuePertenece extends Criterio{

	Comuna comunaAsociada;
	
	
	public List<Terminal> filtrarTerminales(ArrayList<Terminal> terminales) {
		
	return	terminales.stream().filter(unaT->unaT.getComuna().equals(comunaAsociada)).collect(Collectors.toList());
	}


	public Comuna getComunaAsociada() {
		return comunaAsociada;
	}


	public void setComunaAsociada(Comuna comunaAsociada) {
		this.comunaAsociada = comunaAsociada;
	}

}
