package procesos;

import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class ProcActualizarLocalesComerciales extends Proceso{
	

	public void ejecutar() throws IOException{
		//por ahora depende mucho de ese archivo, deberia ser configurable la ruta de actualizacion
		BufferedReader br = new BufferedReader(new FileReader("nuevasPalabrasClavesDeLocalesComerciales.txt"));
		String linea;
		while ((linea = br.readLine()) != null) {
			procesarLinea(linea);
		}
		br.close();
	}
	
	protected void procesarLinea(String linea){
		String [] comercialVectorizado;
		List<String> tagsParaActualizar = new ArrayList<>();
		comercialVectorizado=linea.split(";");
		String nombreLocalComercial ="";
		nombreLocalComercial=comercialVectorizado[0];
		String tagsVectorizados= comercialVectorizado[1];
		String [] tagsSpliteados=tagsVectorizados.split(" ");
		
		for(Integer i=0;i<tagsSpliteados.length;i++){
			tagsParaActualizar.add(tagsSpliteados[i]);
		}
		this.actualizarLocalesComerciales(nombreLocalComercial,tagsParaActualizar);
	}
		
	protected void actualizarLocalesComerciales(String nombreLocalComercial, List<String> tagsParaActualizar) {
		Integer resultados=this.getMapa().actualizarTagsDe(nombreLocalComercial, tagsParaActualizar);
		this.sumarElementosAfectados(resultados);	
	}

	
}