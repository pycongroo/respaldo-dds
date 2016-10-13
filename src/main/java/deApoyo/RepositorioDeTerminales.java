package deApoyo;

import java.util.ArrayList;

import ar.edu.TPPOI.Terminal;

public class RepositorioDeTerminales {

	private static RepositorioDeTerminales repo = new RepositorioDeTerminales();
	 ArrayList<Terminal> terminales = new ArrayList<Terminal>();
	 
	 private RepositorioDeTerminales(){}
	 
	 public static RepositorioDeTerminales getSingletonInstance(){
		 		if (repo == null){
		 			repo = new RepositorioDeTerminales();
		 		}
		 		return repo;
			}
	public void agregarTerminal(Terminal terminal) {
		this.terminales.add(terminal);
	}
	
	public ArrayList<Terminal> getTerminales(){
		return terminales;
	}

	public void clean(){
		repo = null;
	}
}
