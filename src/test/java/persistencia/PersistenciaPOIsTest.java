package persistencia;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import java.util.List;
import ar.edu.TPPOI.MapaPOI;
import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.Comparador;
import pois.CGP;
import pois.LocalComercial;
import pois.POI;
import pois.ParadaDeColectivo;
import pois.SucursalBanco;

public class PersistenciaPOIsTest {
	static SoporteDeInstanciasParaTestsBuilder soporteTest;
	static ParadaDeColectivo parada114Lugano;
	static LocalComercial lcStarbucks;
	static SucursalBanco bancoCiudad;
	static CGP cgpC5;
	static EntityManager entityManager;
	static EntityTransaction tx;
	static MapaPOI mapa;
	
	
	@BeforeClass
	public static void init(){
		soporteTest = new SoporteDeInstanciasParaTestsBuilder();
		parada114Lugano = soporteTest.paradaDeColectivo114DeLugano();
		lcStarbucks = soporteTest.starbucksCoronelDiaz1400();
		bancoCiudad = soporteTest.bancoCiudadCabildoYCongreso();
		cgpC5 = soporteTest.cgpComuna5();
		entityManager = PerThreadEntityManagers.getEntityManager();
		tx = entityManager.getTransaction();
		mapa = new MapaPOI();//soporteTest.mapa();
		persistirParadaDeColectivo114();	//id = 1
		persistirLocalComercialStarbucks();	//id = 2
		persistirCGP5();					//id = 3
		persistirSucursalBancoCiudad();		//id = 4
//		mapa.getListaDePOIs().clear();
	}
	
	public static void persistirParadaDeColectivo114(){
		tx.begin();
		parada114Lugano.setTag("economico");
		entityManager.persist(parada114Lugano.getCoordenada());
		entityManager.persist(parada114Lugano.getDireccion());
		entityManager.persist(parada114Lugano);
		tx.commit();
	}
	
	public static void persistirLocalComercialStarbucks(){
		tx.begin();
		entityManager.persist(lcStarbucks.getCoordenada());
		entityManager.persist(lcStarbucks.getDireccion());
		entityManager.persist(lcStarbucks);
		tx.commit();
	}
	
	public static void persistirCGP5(){
		tx.begin();
		entityManager.persist(cgpC5.getDireccion());
		entityManager.persist(cgpC5);
		tx.commit();
	}
	
	public static void persistirSucursalBancoCiudad(){
		tx.begin();
		entityManager.persist(bancoCiudad.getCoordenada());
		entityManager.persist(bancoCiudad.getDireccion());
		entityManager.persist(bancoCiudad);
		tx.commit();
	}

//	public static 
	
	
	
	@Test
	public void traerParadaColectivo114(){
		ParadaDeColectivo paradaObtenida = entityManager.find(ParadaDeColectivo.class, 1l);
		Assert.assertTrue(Comparador.mismoPOI(parada114Lugano,paradaObtenida));
	}
	
	@Test
	public void traerLocalComercialStarbucks(){
		LocalComercial localObtenido = entityManager.find(LocalComercial.class, 2l);
		Assert.assertTrue(Comparador.mismoLocalComercial(lcStarbucks, localObtenido));
	}
	
	@Test
	public void traerCGP5(){
		CGP cgpObtenido = entityManager.find(CGP.class, 3l);
		Assert.assertTrue(Comparador.mismoPOI(cgpC5, cgpObtenido));
	}
	
	@Test
	public void traerBancoCiudad(){
		SucursalBanco bancoObtenido = entityManager.find(SucursalBanco.class, 4l);
		Assert.assertTrue(Comparador.mismoPOI(bancoCiudad, bancoObtenido));
	}
	
	public void persistirTodosLosPois(){
		persistirCGP5();
		persistirLocalComercialStarbucks();
		persistirParadaDeColectivo114();
		persistirSucursalBancoCiudad();
	
	}
	@Test
	public void importarPoisAlMapaPOI(){
		persistirTodosLosPois();
		MapaPOI mapa = new MapaPOI();
		Assert.assertEquals(4, mapa.getListaDePOIs().size());
	}
	@Test
	public void encuentraLocalComercialEnElMapaPOI(){
		persistirLocalComercialStarbucks();
		MapaPOI mapa = new MapaPOI();
		Assert.assertEquals(lcStarbucks,mapa.buscarPoi(lcStarbucks));
	}
	@Test
	public void nombreDeLocalComercialEnElMapaPOIEstaOK(){
		persistirLocalComercialStarbucks();
		MapaPOI mapa = new MapaPOI();
		Assert.assertEquals(lcStarbucks.getNombre(),mapa.buscarPoi(lcStarbucks).getNombre());
	}
	
}
