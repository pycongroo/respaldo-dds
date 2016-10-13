package procesos;

import java.time.LocalDateTime;

public class ResultadoDelProceso {
	
	Integer cantidadDeElementosAfectados;
	LocalDateTime fechaYHoraEjecucion;
	boolean resultadoDeLaEjecucion;

	
	public ResultadoDelProceso(LocalDateTime fecha, Integer elementosAfectados, boolean resultadoDeLaEjecucion) {
		this.setCantidadDeElementosAfectados(elementosAfectados);
		this.setFechaYHoraEjecucion(fecha);
		this.setResultadoDeLaEjecucion(resultadoDeLaEjecucion);
	}
	public Integer getCantidadDeElementosAfectados() {
		return cantidadDeElementosAfectados;
	}
	public void setCantidadDeElementosAfectados(Integer cantidadDeElementosAfectados) {
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados;
	}
	public LocalDateTime getFechaYHoraEjecucion() {
		return fechaYHoraEjecucion;
	}
	public void setFechaYHoraEjecucion(LocalDateTime fechaYHoraEjecucion) {
		this.fechaYHoraEjecucion = fechaYHoraEjecucion;
	}
	public boolean isResultadoDeLaEjecucion() {
		return resultadoDeLaEjecucion;
	}
	public void setResultadoDeLaEjecucion(boolean resultadoDeLaEjecucion) {
		this.resultadoDeLaEjecucion = resultadoDeLaEjecucion;
	}
	
	public void agregarElementosAfectados(Integer unaCant){
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados + unaCant;
	}
	
	
}
