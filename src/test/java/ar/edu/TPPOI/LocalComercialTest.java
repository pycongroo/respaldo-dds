package ar.edu.TPPOI;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.Punto;
import pois.LocalComercial;

public class LocalComercialTest {

	Punto coordenadaMia, coordenadaCercaStarbucks;
	LocalComercial starbucks;
	LocalComercial cineAbasto;

	@Before
	public void init() {

		SoporteDeInstanciasParaTestsBuilder soporteParaTests = new SoporteDeInstanciasParaTestsBuilder();

		coordenadaMia = soporteParaTests.miCoordenaAbasto();
		coordenadaCercaStarbucks = new Punto(-58.414099, -34.593686);
		cineAbasto= soporteParaTests.cineAbasto();
		starbucks = soporteParaTests.starbucksCoronelDiaz1400();
	}

	@Test
	public void testStarbucksNoEstaCercaDeMiCoordenada() {
		Assert.assertFalse(starbucks.estasCercaDe(coordenadaMia));
	}

	@Test
	public void testStarbucksEstaCercaDeCoordenadaCercaStarbucks() {
		Assert.assertTrue(starbucks.estasCercaDe(coordenadaCercaStarbucks));
	}

	@Test
	public void testLocalDisponible() {
		Assert.assertTrue(starbucks.estaDisponible(LocalDateTime.of(2016, 1, 14, 10, 10, 30)));
	}

	@Test
	public void testStarbuckCambiaAStarbacks() {
		Assert.assertTrue(starbucks.getNombre().equals("Starbucks"));
		starbucks.setNombre("starbacks");
		Assert.assertTrue(starbucks.getNombre().equals("starbacks"));
		Assert.assertFalse(starbucks.getNombre().equals("Starbucks"));
	}
	@Test
	public void estaBienElNombre(){
		Assert.assertTrue(cineAbasto.getNombre().equals("cine Abasto"));
	}
	@Test
	public void tiene1Tag(){
		Assert.assertEquals(1,cineAbasto.getTags().size(),0);
	}
}