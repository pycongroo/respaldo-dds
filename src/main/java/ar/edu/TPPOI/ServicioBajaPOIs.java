package ar.edu.TPPOI;

import java.util.ArrayList;
import java.util.List;

public class ServicioBajaPOIs {
	
	List<String> poisADarDeBaja = new ArrayList<>();
	
	public List<String> getPOIsADarDeBaja(){
		return poisADarDeBaja;
	}
	
	public void agregarNombreDePOIADarDeBaja(String nombreDePOI){
		poisADarDeBaja.add(nombreDePOI);
	}
}
