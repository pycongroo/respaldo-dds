package ar.edu.TPPOI;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import acciones.Almacenar;
import acciones.Notificar;
import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import criteriosFiltradoTerminales.ComunaALaQuePertenece;
import criteriosFiltradoTerminales.TodosLosUsuarios;
import criteriosFiltradoTerminales.UsuariosElegidosPorAdmin;
import deApoyo.Comuna;
import deApoyo.RepositorioDeTerminales;
import manejoErrores.EnvioDeMail;
import procesos.ProcAgregarAccionesParaUsuarios;
import procesos.ProcQuitarAccionesParaUsuarios;


public class AgregarQuitarAccionesTest {
	
	EnvioDeMail envioDeMail1;
	MapaPOI mapaInteractivo;
	ProcAgregarAccionesParaUsuarios procAgregarAcciones;
	ProcQuitarAccionesParaUsuarios procQuitarAcciones;
	Notificar accionNotificar;
	Almacenar accionAlmacenar;
	Terminal terminalAbasto;
	Terminal terminalCaballito;
	Terminal terminalDevoto;
	Terminal terminalBelgrano;
	ComunaALaQuePertenece comunaCriterio; 
	TodosLosUsuarios todosUsersCriterio;
	UsuariosElegidosPorAdmin adminCriterio;
	Comuna comunaAbasto;
	Comuna comunaCaballito;
	

	@Before
	public void init(){
		SoporteDeInstanciasParaTestsBuilder soporteParaTests = new SoporteDeInstanciasParaTestsBuilder();
		
		envioDeMail1 = soporteParaTests.envioDeMail();
		accionNotificar = soporteParaTests.notificar(envioDeMail1);
		accionAlmacenar = soporteParaTests.almacenar();
		
		mapaInteractivo = soporteParaTests.mapa();
		
		procAgregarAcciones = soporteParaTests.procAgregarAcciones();
		procQuitarAcciones = soporteParaTests.procQuitarAcciones();

		comunaAbasto = soporteParaTests.crearComunaAbasto();
		comunaCaballito = soporteParaTests.crearComunaCaballito();
		terminalAbasto = soporteParaTests.terminal();
		terminalCaballito = soporteParaTests.terminal();
		terminalDevoto = soporteParaTests.terminal();
		terminalBelgrano = soporteParaTests.terminal();
		terminalAbasto.setComuna(comunaAbasto);
		terminalCaballito.setComuna(comunaCaballito);
		terminalDevoto.setComuna(comunaAbasto);
		terminalBelgrano.setComuna(comunaAbasto);
		terminalDevoto.activarAccion(accionAlmacenar);
		terminalDevoto.activarAccion(accionNotificar);
		terminalBelgrano.activarAccion(accionAlmacenar);
		terminalBelgrano.activarAccion(accionNotificar);
		
		todosUsersCriterio = soporteParaTests.todosUsersCriterio();
		
		adminCriterio = soporteParaTests.adminCriterio();
		
		comunaCriterio = soporteParaTests.comunaCriterio();
		comunaCriterio.setComunaAsociada(comunaAbasto);
		
		RepositorioDeTerminales.getSingletonInstance().agregarTerminal(terminalAbasto);
		RepositorioDeTerminales.getSingletonInstance().agregarTerminal(terminalDevoto);
		RepositorioDeTerminales.getSingletonInstance().agregarTerminal(terminalCaballito);
		RepositorioDeTerminales.getSingletonInstance().agregarTerminal(terminalBelgrano);
		
		
	}
	
	//------------------------------ Tests de Agregar/Quitar
	// ---------------------ITEM 1
	@Test
	public void testAgregarAccionesAUsuariosSegunComuna(){
		procAgregarAcciones.setCriterio(comunaCriterio);
		procAgregarAcciones.agregarAccion(accionAlmacenar);
		procAgregarAcciones.agregarAccion(accionNotificar);
		procAgregarAcciones.run();
		Assert.assertEquals(3, procAgregarAcciones.terminales().size(),0);
	}

	@Test 
	public void testQuitarAccionesAUsuariosSegunComuna(){
		procQuitarAcciones.setCriterio(comunaCriterio);
		procQuitarAcciones.agregarAccion(accionAlmacenar);
		procQuitarAcciones.agregarAccion(accionNotificar);
		procQuitarAcciones.run();
		Assert.assertEquals(0, terminalDevoto.getAcciones().size(),0);
		Assert.assertEquals(0, terminalAbasto.getAcciones().size(),0);
	}
	
	// ------------------------- ITEM 2
	@Test 
	public void testAgregarAccionesAUsuariosSegunTodosLosUsuarios(){
		procAgregarAcciones.setCriterio(todosUsersCriterio);
		procAgregarAcciones.agregarAccion(accionAlmacenar);
		procAgregarAcciones.run();
		Assert.assertEquals(1, terminalCaballito.getAcciones().size(),0);
		Assert.assertEquals(1, terminalAbasto.getAcciones().size(),0);
		Assert.assertEquals(2, terminalDevoto.getAcciones().size(),0);
		Assert.assertEquals(2, terminalBelgrano.getAcciones().size(),0);
	}
	
	@Test 
	public void testQuitarAccionesAUsuariosSegunTodosLosUsuarios(){
		procQuitarAcciones.setCriterio(todosUsersCriterio);
		procQuitarAcciones.agregarAccion(accionNotificar);
		procQuitarAcciones.run();
		Assert.assertEquals(true, procQuitarAcciones.terminales().contains(terminalAbasto));
	}
	
	@Test 
	public void testQuitarAccionesAUsuariosSegunTodosLosUsuarios2(){
		procQuitarAcciones.setCriterio(todosUsersCriterio);
		procQuitarAcciones.agregarAccion(accionNotificar);
		procQuitarAcciones.run();
		Assert.assertEquals(4,procQuitarAcciones.terminales().size(),0);
	}

	// ------------------------------ ITEM 3 
	
	@Test 
	public void testAgregarAccionesAUsuariosSegunAdmin(){
		procAgregarAcciones.setCriterio(adminCriterio);
		adminCriterio.agregarTerminalesElegidasPorAdmin(terminalAbasto);
		adminCriterio.agregarTerminalesElegidasPorAdmin(terminalCaballito);
		procAgregarAcciones.agregarAccion(accionAlmacenar);
		procAgregarAcciones.agregarAccion(accionNotificar);
		procAgregarAcciones.run();
		Assert.assertEquals(2, terminalCaballito.getAcciones().size(),0);
		Assert.assertEquals(2, terminalAbasto.getAcciones().size(),0);
	}
	
	@Test 
	public void testQuitarAccionesAUsuariosSegunAdmin(){
		procQuitarAcciones.setCriterio(adminCriterio);
		adminCriterio.agregarTerminalesElegidasPorAdmin(terminalDevoto);
		adminCriterio.agregarTerminalesElegidasPorAdmin(terminalBelgrano);
		procQuitarAcciones.agregarAccion(accionAlmacenar);
		procQuitarAcciones.agregarAccion(accionNotificar);
		procQuitarAcciones.run();
		Assert.assertEquals(0, terminalDevoto.getAcciones().size(),0);
		Assert.assertEquals(0, terminalBelgrano.getAcciones().size(),0);
	}
	
	@Test 
	public void testQuitarAccionesAUsuariosSegunAdmin2(){
		procQuitarAcciones.setCriterio(adminCriterio);
		adminCriterio.agregarTerminalesElegidasPorAdmin(terminalDevoto);
		adminCriterio.agregarTerminalesElegidasPorAdmin(terminalBelgrano);
		procQuitarAcciones.agregarAccion(accionAlmacenar);
		procQuitarAcciones.agregarAccion(accionNotificar);
		procQuitarAcciones.run();
		Assert.assertEquals(2, procQuitarAcciones.terminales().size(),0);
	
	}
	

	@After
	public void after(){
	RepositorioDeTerminales.getSingletonInstance().clean();
	}
}
