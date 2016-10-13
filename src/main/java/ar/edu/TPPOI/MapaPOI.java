package ar.edu.TPPOI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import externos.SistemaExternoAdapterInterface;
import pois.POI;
import procesos.ProcDarDeBajaPOIs;

public class MapaPOI {
	
	public MapaPOI(){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		TypedQuery<POI> query = entityManager.createQuery("SELECT p FROM POI p", POI.class);
		List<POI> pois = query.getResultList();
		this.agregarListaDePOI(pois);
	}

	List<POI> listaDePOIs = new ArrayList<>();
	List<SistemaExternoAdapterInterface> listaDeSistemaExternoAdapter = new ArrayList<>();

	public List<POI> getListaDePOIs() {
		return listaDePOIs;
	}

	public List<POI> buscar(String textoLibre) {
		List<POI> resultadoBusquedaLocal = this.busquedaLocal(textoLibre);
		if (resultadoBusquedaLocal.isEmpty()) {
			List<POI> listaDePOIsExternos = this.buscarEnSistemasExternos(textoLibre);
			this.actualizarPOIsSiCorresponde(listaDePOIsExternos);
			resultadoBusquedaLocal = this.busquedaLocal(textoLibre);
		}
		return resultadoBusquedaLocal;
	}

	public List<POI> busquedaLocal(String textoLibre) {
		return this.getListaDePOIs().stream().filter(unPOI -> unPOI.busqueda(textoLibre)).collect(Collectors.toList());
	}

	public List<POI> buscarEnSistemasExternos(String textoLibre) {
		List<POI> listaDePOIsExternos = new ArrayList<>();
		listaDeSistemaExternoAdapter.stream().forEach(
				unSistemaExternoAdapter -> listaDePOIsExternos.addAll(unSistemaExternoAdapter.buscar(textoLibre)));
		return listaDePOIsExternos;
	}

	public void agregarPOI(POI poi) {
		listaDePOIs.add(poi);
	}

	public void agregarListaDePOI(List<POI> pois) {
		listaDePOIs.addAll(pois);
	}
	
	public void borrarPOI(POI poi) {
		listaDePOIs.remove(poi);
	}

	private void actualizarPOIsSiCorresponde(List<POI> unaListaDePOIs) {
		unaListaDePOIs.stream().forEach(unPOI -> actualizarPOISiCorresponde(unPOI));
	}

	private void actualizarPOISiCorresponde(POI unPOIExterno) {
		if (estaEnLocal(unPOIExterno)) {
			buscarPoi(unPOIExterno).actualizar(unPOIExterno);
		} else {
			agregarPOI(unPOIExterno);
		}
	}

	public POI buscarPoi(POI poi) {
		return listaDePOIs.stream().filter(unPOILocal -> unPOILocal.soyElMismoPOI(poi)).findFirst().orElse(null);
	}

	public boolean estaEnLocal(POI unPOIExterno) {
		return buscarPoi(unPOIExterno) != null;
	}

	public void agregarSistemaExternoAdapter(SistemaExternoAdapterInterface unSistemaExternoAdapter) {
		listaDeSistemaExternoAdapter.add(unSistemaExternoAdapter);
	}

	public void borrarSistemaExternoAdapter(SistemaExternoAdapterInterface unSistemaExternoAdapter) {
		listaDeSistemaExternoAdapter.remove(unSistemaExternoAdapter);
	}


	List<String> obtenerTagsDelPOI(String nombreLocalComercial) {
		return this.getListaDePOIs().stream().filter(unPOI->unPOI.getNombre().equals("cine Abasto")).
				collect(Collectors.toList()).get(0).getTags();
	}

	public POI obtenerPOI(String nombreLocalComercial) {
			return this.getListaDePOIs().stream().filter(unP->unP.getNombre().equals(nombreLocalComercial))
					.collect(Collectors.toList()).get(0);
	}
	
	public void eliminarPOIs(List<String> nombresDeLosPOIs, ProcDarDeBajaPOIs proceso){
		nombresDeLosPOIs.forEach(unNombreDePOI -> this.eliminarElPOI(unNombreDePOI, proceso));
	}

	public void eliminarElPOI(String unNombreDePOI, ProcDarDeBajaPOIs proceso){
		POI poiEncontrado = this.obtenerPOI(unNombreDePOI);
		this.borrarPOI(poiEncontrado);
		proceso.sumarElementosAfectados(1);
	}

	public Integer actualizarTagsDe(String nombreLocalComercial, List<String> tagsParaActualizar) {
		if (this.mapaContieneElPOI(nombreLocalComercial)){
			POI unPOI=this.obtenerPOI(nombreLocalComercial);
			return this.actualizarTags(unPOI, tagsParaActualizar);
		}else{
			return 0;
		}
	}
	public Integer actualizarTags(POI unPOI, List<String> tagsParaActualizar) {
		if (unPOI.getTags().equals(tagsParaActualizar)){
			return 0;
				}else{
					unPOI.setTags(tagsParaActualizar);
					return 1;
				}
		
		}

	public boolean mapaContieneElPOI(String nombreLocalComercial){
		return listaDePOIs.stream().anyMatch(unP->unP.getNombre().equals(nombreLocalComercial));
	}
}