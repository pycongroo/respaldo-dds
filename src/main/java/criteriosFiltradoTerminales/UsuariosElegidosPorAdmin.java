package criteriosFiltradoTerminales;

import java.util.ArrayList;

import ar.edu.TPPOI.Terminal;


public class UsuariosElegidosPorAdmin extends Criterio {

	ArrayList<Terminal> terminalesElegidasPorAdmin= new ArrayList<>();
	public ArrayList<Terminal> getTerminalesElegidasPorAdmin() {
		return terminalesElegidasPorAdmin;
	}
	public void agregarTerminalesElegidasPorAdmin(Terminal terminalElegidasPorAdmin) {
		this.terminalesElegidasPorAdmin.add(terminalElegidasPorAdmin);
	}
	
	public ArrayList<Terminal> filtrarTerminales(ArrayList<Terminal> terminales) {
	return terminalesElegidasPorAdmin;
	}


}
