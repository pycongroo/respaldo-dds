package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.TPPOI.Terminal;
import clasesParaTests.EnvioMailImpostor;
import clasesParaTests.SoporteDeInstanciasParaTestsBuilder;
import deApoyo.Comparador;

public class PersistenciaTerminalesTest {
	
	EntityManager entityManager;
	EntityTransaction tx;
	SoporteDeInstanciasParaTestsBuilder soporteTest;
	Terminal terminal;

	@Before
	public void init(){
		entityManager = PerThreadEntityManagers.getEntityManager();
		soporteTest = new SoporteDeInstanciasParaTestsBuilder();
		terminal = soporteTest.terminal();
		terminal.setDescripcion("Abasto");
		terminal.setMapa(soporteTest.mapa());
		terminal.setComuna(soporteTest.crearComunaAbasto());
		terminal.activarAccion(soporteTest.almacenar());
		terminal.activarAccion(soporteTest.notificar(new EnvioMailImpostor()));
		terminal.buscar("114");
		tx = entityManager.getTransaction();
		persistirTerminal();
	}


	public void persistirTerminal(){
		tx.begin();
		entityManager.persist(terminal);
		tx.commit();
	}
	
	@Test
	public void traerTerminalTest(){
		Terminal terminalObtenida = entityManager.find(Terminal.class, 1l);
		Assert.assertTrue(Comparador.mismaTerminal(terminalObtenida, terminal));
	}
	
	

}
