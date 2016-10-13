package procesos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import acciones.Accion;
import ar.edu.TPPOI.Terminal;
import criteriosFiltradoTerminales.Criterio;
import deApoyo.RepositorioDeTerminales;

public abstract class ProcesoAltaBaja extends Proceso {
	Set<Accion> acciones = new HashSet<Accion>();
	
	ArrayList<Terminal> terminalesFiltradas = new ArrayList<>();
	Criterio criterio;
	

	public ArrayList<Terminal> getTerminalesFiltradas(){
		return terminalesFiltradas;
	}


	public Set<Accion> getAcciones() {
		return acciones;
	}


	public void agregarAccion(Accion accion) {
		acciones.add(accion);
	}


	public Criterio getCriterio() {
		return criterio;
	}


	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}


	public List<Terminal> terminales() {
			return this.getCriterio().filtrarTerminales(RepositorioDeTerminales.getSingletonInstance().getTerminales());		
	}

}
