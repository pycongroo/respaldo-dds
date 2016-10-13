package persistencia;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.TPPOI.Terminal;
import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import excepciones.NoEstaEnCacheException;
import pois.POI;

public class PersistenciaMongoTest {

	private Terminal terminal;
	private SoporteDeInstanciasParaTestsBuilder soporte;
	
	@Before
	public void init(){
		soporte = new SoporteDeInstanciasParaTestsBuilder();
		terminal = new Terminal();
		terminal.setMapa(soporte.mapa());
		terminal.buscar("114");
	}
	
	@Test(expected=NoEstaEnCacheException.class)
	public void cacheVacia() throws NoEstaEnCacheException{
		terminal.limpiarCache();
		terminal.buscarEnCache("114");
	}
	
	@Test
	public void buscar() throws NoEstaEnCacheException{
		List<POI> pois = terminal.buscarEnCache("114");
		Assert.assertEquals(2, pois.size());
	}
	
	@After
	public void dropCollections(){
		terminal.limpiarCache();
	}
}
