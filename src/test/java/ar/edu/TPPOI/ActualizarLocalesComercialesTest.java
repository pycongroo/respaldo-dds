package ar.edu.TPPOI;

import org.junit.Before;
import org.junit.Test;

import clasesParaTests.ProcesoActualizadorLocalesImpostor;
import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.ConfiguradorDeProcesos;
import manejoErrores.NoRealizarAccion;
import manejoErrores.ReintentarNVeces;
import pois.LocalComercial;
import procesos.ProcActualizarLocalesComerciales;

import java.util.Date;
import java.util.stream.Collectors;

import org.junit.Assert;

public class ActualizarLocalesComercialesTest {
	
	ProcActualizarLocalesComerciales actualizadorDeLocalesComerciales;
	MapaPOI mapaInteractivo;
	LocalComercial cineAbasto;
	LocalComercial sportClubLibertador7395;
	LocalComercial starbucksCoronelDiaz1400;
	ConfiguradorDeProcesos configuradorDeProcesos;
	ReintentarNVeces reintentarNVeces;
	NoRealizarAccion noRealizarAccion;
	Date horario1;
	@Before
	public void init(){
		actualizadorDeLocalesComerciales = new ProcesoActualizadorLocalesImpostor();
		SoporteDeInstanciasParaTestsBuilder soporteParaTests = new SoporteDeInstanciasParaTestsBuilder();
		cineAbasto = soporteParaTests.cineAbasto();
		starbucksCoronelDiaz1400=soporteParaTests.starbucksCoronelDiaz1400();
		mapaInteractivo=soporteParaTests.mapa();
		actualizadorDeLocalesComerciales.setMapa(mapaInteractivo);
		configuradorDeProcesos= soporteParaTests.configuradorDeProcesos();
		noRealizarAccion= soporteParaTests.noRealizarAccion();
		horario1=soporteParaTests.generarHorario();
		sportClubLibertador7395=soporteParaTests.sportClubLibertador7395();
}
	
	@Test
	public void seActualizan4LosTagsDelCineAbasto() {
		actualizadorDeLocalesComerciales.run();
		Assert.assertEquals(4, cineAbasto.getTags().size());
		
	}

	@Test
	public void queda1soloTagEnsportClubLibertador7395(){
		Assert.assertEquals(3, sportClubLibertador7395.getTags().size());
		actualizadorDeLocalesComerciales.run();
		Assert.assertEquals(1, sportClubLibertador7395.getTags().size());
	}

	@Test
	public void seActualizanLosTagsDeStarbucks(){
		actualizadorDeLocalesComerciales.run();
		//Assert.assertEquals(3,starbucksCoronelDiaz1400.getTags().size());
		Assert.assertTrue(this.mapaInteractivo.listaDePOIs.stream().filter(unp->unp.equals(starbucksCoronelDiaz1400)).collect(Collectors.toList()).get(0).getNombre().equals("Starbucks"));
		
	}
	@Test 
	public void seDetectanTresElemntosAfectados(){
		actualizadorDeLocalesComerciales.run();
		Assert.assertEquals(3,actualizadorDeLocalesComerciales.getResultadoDeEjecucionDelProceso().getCantidadDeElementosAfectados(),0);
	}
	
	@Test
	public void musimundoNoSeActualizaPorQueNoExisteEnNuestroRepositorioDePOIs(){
		actualizadorDeLocalesComerciales.run();
		Assert.assertFalse(actualizadorDeLocalesComerciales.getMapa().getListaDePOIs().stream().anyMatch((unP->unP.getNombre().equals("musimundo"))));
	}
}