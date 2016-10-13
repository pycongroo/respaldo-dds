package ar.edu.TPPOI;

import org.junit.Before;
import org.junit.Test;

import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.ConfiguradorDeProcesos;
import manejoErrores.NoRealizarAccion;
import pois.LocalComercial;
import procesos.ProcDarDeBajaPOIs;

import java.time.LocalDateTime;

import org.junit.Assert;

public class BajaDePOIsTest {

	ProcDarDeBajaPOIs procesoBajaDePOIs;
	ProcDarDeBajaPOIs procesoBajaDePOIs2;
	MapaPOI mapaInteractivo;
	LocalComercial cineAbasto;
	ConfiguradorDeProcesos configuradorDeProcesos;
	NoRealizarAccion noRealizarAccion;
	ServicioBajaPOIs servicioBajaDePOIs;
	ServicioBajaPOIs servicioBajaDePOIs2;
	
	@Before
	public void init(){
		SoporteDeInstanciasParaTestsBuilder soporteParaTests = new SoporteDeInstanciasParaTestsBuilder();
		cineAbasto = soporteParaTests.cineAbasto();
		mapaInteractivo=soporteParaTests.mapa();
		procesoBajaDePOIs = soporteParaTests.procesoBajaDePOIs();
		procesoBajaDePOIs2 = soporteParaTests.procesoBajaDePOIs();
		servicioBajaDePOIs = soporteParaTests.servicioBajaDePOIs();
		servicioBajaDePOIs2 = soporteParaTests.servicioBajaDePOIs();
		procesoBajaDePOIs.setMapa(mapaInteractivo);
		procesoBajaDePOIs2.setMapa(mapaInteractivo);
		procesoBajaDePOIs.setServicioDeBaja(servicioBajaDePOIs);
		procesoBajaDePOIs2.setServicioDeBaja(servicioBajaDePOIs2);
		configuradorDeProcesos= soporteParaTests.configuradorDeProcesos();
		noRealizarAccion= soporteParaTests.noRealizarAccion();
	
		procesoBajaDePOIs.setAccionEnCasoDeError(noRealizarAccion);
		procesoBajaDePOIs2.setAccionEnCasoDeError(noRealizarAccion);
	}

//-------------------------------Tests para probar el proceso de Dar de Baja-------------------------------
	@Test
	public void testDarDeBajaUnPOIExistente(){
		servicioBajaDePOIs.agregarNombreDePOIADarDeBaja("Banco Ciudad");
		procesoBajaDePOIs.run();
		Assert.assertEquals(0, mapaInteractivo.buscar("Banco Ciudad").size());
	}
	
	@Test
	public void testSeDetecta1ElementoAfectadoPorHaberEliminado1POI(){
		servicioBajaDePOIs.agregarNombreDePOIADarDeBaja("Banco Ciudad");
		procesoBajaDePOIs.run();
		Assert.assertEquals(1, procesoBajaDePOIs.getResultadoDeEjecucionDelProceso().getCantidadDeElementosAfectados(), 0);
	}
	
	@Test
	public void testNoSeDioDeBajaUnPOIPorqueNoEstabaEnElMapa(){
		servicioBajaDePOIs.agregarNombreDePOIADarDeBaja("lala");
		procesoBajaDePOIs.run();
		Assert.assertEquals(procesoBajaDePOIs.getResultadoDeEjecucionDelProceso().isResultadoDeLaEjecucion(), false);
	}
	
//----------Test de ejecucion automatica de procesos en base al horario definido----------		

	@Test
	public void testEjecucionDeLos2ProcesosAgregadosAlBatch(){
		servicioBajaDePOIs.agregarNombreDePOIADarDeBaja("Banco Ciudad");
		servicioBajaDePOIs2.agregarNombreDePOIADarDeBaja("SportClub");
		
		LocalDateTime fechaYHora = LocalDateTime.now().minusMinutes(10);
		LocalDateTime fechaYHora2 = LocalDateTime.now();
		
		configuradorDeProcesos.agregarProcesoAlBatch(procesoBajaDePOIs, fechaYHora);
		configuradorDeProcesos.agregarProcesoAlBatch(procesoBajaDePOIs2, fechaYHora2);
		
		configuradorDeProcesos.work();
	
		Assert.assertEquals(mapaInteractivo.buscar("Banco Ciudad").size(), 0);
		Assert.assertEquals(mapaInteractivo.buscar("SportClub").size(), 0);	
	
	}
	
	@Test
	public void testNoEjecucionDeProcesoPorTenerFechaPosterior(){
		servicioBajaDePOIs.agregarNombreDePOIADarDeBaja("Banco Ciudad");

		LocalDateTime fechaYHora = LocalDateTime.now().plusHours(50);
		configuradorDeProcesos.agregarProcesoAlBatch(procesoBajaDePOIs, fechaYHora);
		
		configuradorDeProcesos.work();
	
		Assert.assertEquals(mapaInteractivo.buscar("Banco Ciudad").size(), 1);

	}
	
}
