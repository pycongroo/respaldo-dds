package morphia;

import java.util.ArrayList;
import java.util.List;

import pois.POI;

public class ElementoCache {

	private String fraseBuscada;
	private List<POI> poisEncontrados;
	
	public ElementoCache(){
		poisEncontrados = new ArrayList<>();
	}

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}

	public List<POI> getPoisEncontrados() {
		return poisEncontrados;
	}

	public void setPoisEncontrados(List<POI> poisEncontrados) {
		this.poisEncontrados = poisEncontrados;
	}
	
}
