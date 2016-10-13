package procesos;

import ar.edu.TPPOI.Terminal;

public class ProcQuitarAccionesParaUsuarios extends ProcesoAltaBaja {

	public void ejecutar(){
		this.terminales().forEach(unaT->this.desactivarCadaAccionPorTerminal(unaT));
	}
	
	public void desactivarCadaAccionPorTerminal(Terminal unaTerminal){
		acciones.forEach(unaA -> unaTerminal.desactivarAccion(unaA));
	}


}
