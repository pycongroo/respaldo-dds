package ar.edu.TPPOI;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clasesParaTests.ProcesoImpostorParaPruebasDeReintento;
import manejoErrores.ReintentarNVeces;

public class ReintentosPorFallaTest {
	private ProcesoImpostorParaPruebasDeReintento proceso;
	private ReintentarNVeces manejoReintento;
	
	@Before
	public void init(){
		proceso = new ProcesoImpostorParaPruebasDeReintento();
		manejoReintento = new ReintentarNVeces();
		manejoReintento.setVeces(5);
		proceso.setAccionEnCasoDeError(manejoReintento);
	}
	
	@Test
	public void seReintenta4Veces(){
		proceso.fallarHasta(4);
		proceso.run();
		//el proceso ejecuta 1 + 4 reintentos
		Assert.assertEquals(5, proceso.cantidadEjecucionesRealizadas(), 0);
	}
	
	@Test
	public void UsaTodosLosReintentos(){
		proceso.fallarHasta(8);
		proceso.run();
		Assert.assertEquals(6, proceso.cantidadEjecucionesRealizadas(), 0);
	}
	
	@Test
	public void primeraEjecucionExitosa(){
		proceso.fallarHasta(0);
		proceso.run();
		Assert.assertEquals(1, proceso.cantidadEjecucionesRealizadas(), 0);
	}
	
	@Test
	public void noHayReintentos(){
		proceso.fallarHasta(4);
		manejoReintento.setVeces(0);
		proceso.run();
		Assert.assertEquals(1, proceso.cantidadEjecucionesRealizadas(), 0);
	}
	
	
}
