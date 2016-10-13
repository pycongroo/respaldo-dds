package procesos;

import java.io.IOException;
import java.time.LocalDateTime;

import ar.edu.TPPOI.MapaPOI;
import manejoErrores.ManejoDeResultado;

public abstract class Proceso{

	MapaPOI mapa;
	ResultadoDelProceso resultadoDeEjecucionDelProceso;
	ManejoDeResultado accionEnCasoDeError ;

	
	public ManejoDeResultado getAccionesEnCasoDeError() {
		return accionEnCasoDeError;
	}
	public void setAccionEnCasoDeError(ManejoDeResultado accionesEnCasoDeError) {
		this.accionEnCasoDeError = accionesEnCasoDeError;
	}
	public ResultadoDelProceso getResultadoDeEjecucionDelProceso() {
		return resultadoDeEjecucionDelProceso;
	}
	public void setResultadoDeEjecucionDelProceso(ResultadoDelProceso resultadoDeEjecucionDelProceso) {
		this.resultadoDeEjecucionDelProceso = resultadoDeEjecucionDelProceso;
	}

	public MapaPOI getMapa() {
		return mapa;
	}
	public void setMapa(MapaPOI mapa) {
		this.mapa = mapa;
	}

	public void run(){
		this.instanciarResultadoDeEjecucion();
		try
		{
			this.ejecutar();
		}
		catch (Exception e)
		{
			this.ejecutarAccionesDeError();
		}
	}
	
	public void ejecutarAccionesDeError(){
		this.accionEnCasoDeError.ejecutarEnCasoDeFalla(this);
	}
		
	public abstract void ejecutar() throws IOException;
	public void sumarElementosAfectados(Integer unaCant){
		resultadoDeEjecucionDelProceso.agregarElementosAfectados(unaCant);
	}
	
	public void instanciarResultadoDeEjecucion(){
		ResultadoDelProceso resultado=new ResultadoDelProceso(LocalDateTime.now(),0,true);
		this.setResultadoDeEjecucionDelProceso(resultado);
	}
	
}
