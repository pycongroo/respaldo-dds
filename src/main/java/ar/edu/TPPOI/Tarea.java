package ar.edu.TPPOI;

import java.time.LocalDateTime;

import procesos.Proceso;

public class Tarea {

	Proceso proceso;
	LocalDateTime fechaYHora;
	
	public Tarea (Proceso unProceso, LocalDateTime fechaYHora){
		this.proceso = unProceso;
		this.fechaYHora = fechaYHora;
	}
	
	public Proceso getProceso(){
		return this.proceso;
	}
	
	public LocalDateTime getFechaYHora(){
		return this.fechaYHora;
	}
	
	public boolean tieneFechaMenorOIgualAAhora(LocalDateTime unaFechaYHora){
		
		return (fechaYHora.isBefore(unaFechaYHora) || fechaYHora.isEqual(unaFechaYHora));
	}
}
