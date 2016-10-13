package ar.edu.TPPOI;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import excepciones.NoEstaEnCacheException;
import morphia.ElementoCache;
import pois.POI;

public class CachePersistente {
	
	private MongoClient client;
	private Morphia morphia;
	private Datastore ds;
	
	public CachePersistente(long terminalID, int port){
		String nameDB = "terminal" + String.valueOf(terminalID);
		client = new MongoClient("localhost", port);
		morphia = new Morphia();
		morphia.mapPackage("morphia");
		morphia.mapPackage("pois");
		ds = morphia.createDatastore(client, nameDB);
	}
	
	public void guardar(ElementoCache elem){
		ds.save(elem);
	}
	
	public void guardar(List<ElementoCache> elems){
		ds.save(elems);
	}
	
	public List<POI> buscar(String unaFrase) throws NoEstaEnCacheException{
		ElementoCache elem = ds.find(ElementoCache.class).filter("fraseBuscada", unaFrase).get();
		if (elem == null){
//			System.out.println("nulllllll");
			throw new NoEstaEnCacheException();
		}
		return elem.getPoisEncontrados();
	}
	
	public void limpiar(){
		ds.delete(ds.createQuery(ElementoCache.class));
	}
	
}
