package deApoyo;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.TPPOI.Tarea;
import procesos.Proceso;

public class ConfiguradorDeProcesos{

	List<Tarea> tareasEnBatch = new ArrayList<>();

	public void work(){
		List<Tarea> tareasParaEjecutar = this.filtrarTareasAEjecutar();
		
		tareasParaEjecutar.forEach(unaT -> unaT.getProceso().run());
		tareasEnBatch.removeAll(tareasParaEjecutar);
	}
	
	//---------------------------------------------------------
	public void agregarProcesoAlBatch(Proceso unProceso, LocalDateTime fechaYHora){
		Tarea nuevaTarea = new Tarea(unProceso, fechaYHora);
		this.tareasEnBatch.add(nuevaTarea);
	}
	
	public List<Tarea> filtrarTareasAEjecutar(){
		
		return (tareasEnBatch
			   .stream()
			   .filter(unaT -> unaT.tieneFechaMenorOIgualAAhora(LocalDateTime.now()))
			   .collect(Collectors.toList()));
	}
	
	
	
	public List<Tarea> getTareasEnBatch(){
		return this.tareasEnBatch;
	}
}
