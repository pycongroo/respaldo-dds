package procesos;

import java.util.List;

import ar.edu.TPPOI.ServicioBajaPOIs;

import java.util.ArrayList;

public class ProcDarDeBajaPOIs extends Proceso{
	
	private ServicioBajaPOIs servicioBajaDePOIs;
	
	public void ejecutar() {
		List<String> nombresDeLosPOIsABajar = new ArrayList<>();
		nombresDeLosPOIsABajar = servicioBajaDePOIs.getPOIsADarDeBaja();	
		
		this.getMapa().eliminarPOIs(nombresDeLosPOIsABajar, this);
			
	}
	
	public void ejecutarAccionesDeError(){
		super.ejecutarAccionesDeError();
		this.getResultadoDeEjecucionDelProceso().setResultadoDeLaEjecucion(false);
	}
	
	public void setServicioDeBaja(ServicioBajaPOIs servicioBajaDePOIs){
		this.servicioBajaDePOIs = servicioBajaDePOIs;
	}
}
