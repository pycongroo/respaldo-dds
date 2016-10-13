package ar.edu.TPPOI;

import java.util.Calendar;
import java.util.Date;
import org.junit.Before;

import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.ConfiguradorDeProcesos;
import procesos.ProcActualizarLocalesComerciales;

public class ConfigurarProcesosTest {
	ProcActualizarLocalesComerciales actualizadorDeLocalesComerciales;
	ConfiguradorDeProcesos configuradorDeProcesos;
	SoporteDeInstanciasParaTestsBuilder soporteParaTests=new SoporteDeInstanciasParaTestsBuilder();
	Date horario1=new Date();
	Calendar calendario= Calendar.getInstance();
	
	
	@Before
	public void init(){
	/*actualizadorDeLocalesComerciales= soporteParaTests.actualizadorDeLocalesComerciales();
	configuradorDeProcesos= soporteParaTests.configuradorDeProcesos();
	calendario.set(2016, 5, 27, 19, 24, 45);
	System.out.println(calendario);
		}
	
	@Test
	public void seConfiguraUnProcesoParaUnHorarioDeterminado(){
		configuradorDeProcesos.schedule(actualizadorDeLocalesComerciales, calendario);	
	}
*/
}
}