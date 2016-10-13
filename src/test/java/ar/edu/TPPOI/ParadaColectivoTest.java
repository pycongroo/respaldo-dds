package ar.edu.TPPOI;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.Punto;
import pois.ParadaDeColectivo;

public class ParadaColectivoTest {

	Punto coordenadaMia;
	ParadaDeColectivo parada114DeCabildoYMonroe;

	@Before
	public void init() {

		SoporteDeInstanciasParaTestsBuilder soporteParaTests = new SoporteDeInstanciasParaTestsBuilder();

		coordenadaMia = soporteParaTests.miCoordenaAbasto();
		parada114DeCabildoYMonroe = soporteParaTests.paradaDeColectivo114DeCabildoYMonroe();
	}

	@Test
	public void testParadaDeColectivoNoEstaCercaDeMiCoordenada() {
		Assert.assertFalse(parada114DeCabildoYMonroe.estasCercaDe(coordenadaMia));
	}

	@Test
	public void testParadaDeColectivoDisponible() {
		Assert.assertTrue(parada114DeCabildoYMonroe.estaDisponible((LocalDateTime.of(2016, 1, 16, 10, 10, 30))));
	}

}