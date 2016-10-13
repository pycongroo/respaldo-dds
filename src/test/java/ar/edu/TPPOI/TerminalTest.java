package ar.edu.TPPOI;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import acciones.Almacenar;
import acciones.Notificar;
import clasesParaTests.EnvioMailImpostor;
import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.GeneradorDeReportes;



public class TerminalTest {
	Terminal terminalAbasto;
	Terminal terminalCaballito;
	MapaPOI mapaInteractivo;
	Notificar accionNotificar;
	Notificar accionNotificar2;
	Almacenar accionAlmacenar;
	Almacenar accionAlmacenar2;
	EnvioMailImpostor envioDeMail1;
	EnvioMailImpostor envioDeMail2;
	
	@Before
	public void init(){
		SoporteDeInstanciasParaTestsBuilder soporteParaTests = new SoporteDeInstanciasParaTestsBuilder();
		mapaInteractivo = soporteParaTests.mapa();
		terminalAbasto = soporteParaTests.terminal();
		terminalCaballito = soporteParaTests.terminal();
		envioDeMail1 = new EnvioMailImpostor();
		envioDeMail2 = new EnvioMailImpostor();
		accionNotificar = soporteParaTests.notificar(envioDeMail1);
		accionNotificar2 = soporteParaTests.notificar(envioDeMail2);
		accionAlmacenar = soporteParaTests.almacenar();
		accionAlmacenar2 = soporteParaTests.almacenar();
		terminalAbasto.setMapa(mapaInteractivo);
		terminalCaballito.setMapa(mapaInteractivo);

	}

	
//------------------------------ Tests de Activar/Desactivar ------------------------------	

	//Tests casos felices
	@Test
	public void testActivarUnaAccion(){
		accionNotificar.setTiempoLimite(1);
		terminalAbasto.activarAccion(accionNotificar);
		
		Assert.assertEquals(true, terminalAbasto.getAcciones().contains(accionNotificar));
	}

	@Test
	public void testDesactivarUnaAccionExistente(){
		accionNotificar.setTiempoLimite(1);
		terminalAbasto.activarAccion(accionNotificar);
		terminalAbasto.desactivarAccion(accionNotificar);
		
		Assert.assertEquals(false, terminalAbasto.getAcciones().contains(accionNotificar));
	}
	
	
	//Tests casos especiales
	
	@Test 
	public void testQueNoSePuedanAgregarMasDeUnNotificar(){
		accionNotificar.setTiempoLimite(1);
		accionNotificar2.setTiempoLimite(1);
		terminalAbasto.activarAccion(accionNotificar);
		terminalAbasto.activarAccion(accionNotificar2);
		Assert.assertEquals(1,terminalAbasto.getAcciones().size(),0);
	}
	
	@Test 
	public void testQueNoSePuedaSacarUnNotificarSiLaTerminalNoLoTiene(){
		accionNotificar.setTiempoLimite(1);
		accionNotificar2.setTiempoLimite(1);
		terminalAbasto.desactivarAccion(accionNotificar);
		Assert.assertEquals(0,terminalAbasto.getAcciones().size(),0);
	}
	
	
//------------------------------ Tests de Notificar ------------------------------
	@Test
	public void testNotificarCuandoSeExcedeElTiempoLimite(){
		accionNotificar.setTiempoLimite(1);
		terminalAbasto.activarAccion(accionNotificar);
		
		terminalAbasto.buscar("114");
		Assert.assertEquals(true, envioDeMail1.getMailEnviado());
	}
	
	@Test
	public void testNoNotificaCuandoNoSeExcedeElTiempoLimite(){
		accionNotificar.setTiempoLimite(1000000000); //1 segundo
		terminalAbasto.activarAccion(accionNotificar);
		
		terminalAbasto.buscar("114");
		Assert.assertEquals(false, envioDeMail1.getMailEnviado());
	}
	
	
//------------------------------ Tests de Almacenar ------------------------------
	@Test ()
	public void testAlmacenarResultadosDeBusqueda(){
		
		terminalAbasto.activarAccion(accionAlmacenar);
		terminalAbasto.buscar("114");
		Assert.assertEquals("114", terminalAbasto.getBusquedasHechas().get(0).getFrase());
		Assert.assertEquals(2, terminalAbasto.getBusquedasHechas().get(0).getCantDeResultados(), 0);

	}
	
	
//------------------------------ Tests de Reportes ------------------------------
	
	//Tests para item 3
	@Test
	public void testObtenerReporteDeTerminalDadaUnaFecha(){
		terminalAbasto.activarAccion(accionAlmacenar);
		terminalAbasto.buscar("114");
		terminalAbasto.buscar("fitness");
		
		Assert.assertEquals(2, terminalAbasto.obtenerReporte(LocalDate.now()));
		
	}
	
	@Test
	public void testObtenerReporteDeTerminalDadaUnaFechaSinHaberBuscado(){
		terminalAbasto.activarAccion(accionAlmacenar);
		
		Assert.assertEquals(0, terminalAbasto.obtenerReporte(LocalDate.now()));
		
	}
	
	@Test
	public void testObtenerReporteDeTerminalDadaUnaFechaConAlmacenarDesactivado(){
		terminalAbasto.buscar("114");
		terminalAbasto.buscar("fitness");
		
		Assert.assertEquals(0, terminalAbasto.obtenerReporte(LocalDate.now()));
		
	}
	
	
	
	//Tests para item 4a
	@Test
	public void testGenerarReportePorBusqueda(){
		List<Integer> listaQueEspero = new ArrayList<>();
		listaQueEspero.add(2);
		listaQueEspero.add(1);
		
		terminalAbasto.activarAccion(accionAlmacenar);
		terminalAbasto.buscar("114");
		terminalAbasto.buscar("fitness");
		
		Assert.assertEquals(listaQueEspero, terminalAbasto.generarReportePorBusqueda());
		
	}
	
	@Test
	public void testGenerarReportePorBusquedaNoHabiendoBuscado(){
		List<Integer> listaQueEspero = new ArrayList<>();
		
		terminalAbasto.buscar("114");
		terminalAbasto.buscar("fitness");
		Assert.assertEquals(listaQueEspero, terminalAbasto.generarReportePorBusqueda());
		
	}
	
	//Tests para item 4b
	@Test
	public void testGenerarReportesTotalesPorTerminales(){
		List<Terminal> listaDeTerminales = new ArrayList<>();
		listaDeTerminales.add(terminalAbasto);
		listaDeTerminales.add(terminalCaballito);
		
		Map<Terminal, Integer> diccionarioAuxiliar = new HashMap<>();
		diccionarioAuxiliar.put(terminalAbasto, 3);
		diccionarioAuxiliar.put(terminalCaballito, 1);
		
		
		terminalAbasto.activarAccion(accionAlmacenar);
		terminalCaballito.activarAccion(accionAlmacenar);
		
		terminalAbasto.buscar("114");
		terminalAbasto.buscar("fitness");
		
		terminalCaballito.buscar("hola");
		terminalCaballito.buscar("SUBE");
		
		Assert.assertEquals(diccionarioAuxiliar, GeneradorDeReportes.generarReportesTotales(listaDeTerminales));
	}
	
	@After
	public void dropCollections(){
		terminalAbasto.limpiarCache();
		terminalCaballito.limpiarCache();
	}
	
	
	
	
	
	
	
	
	
	
	
}
