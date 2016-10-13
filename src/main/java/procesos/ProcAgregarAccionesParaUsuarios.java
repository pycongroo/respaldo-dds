package procesos;

import ar.edu.TPPOI.Terminal;

public class ProcAgregarAccionesParaUsuarios extends ProcesoAltaBaja{
	
	public void ejecutar(){
		
		this.terminales().forEach(unaT->this.activarCadaAccionPorTerminal(unaT));
	}
	
	public void activarCadaAccionPorTerminal(Terminal unaTerminal){
		acciones.forEach(unaA -> unaTerminal.activarAccion(unaA));
	}


}
