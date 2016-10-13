package ar.edu.TPPOI;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

import deApoyo.Comparador;
import pois.POI;

@Entity
@Table(name="BusquedasHechas")
public class BusquedaHecha {
	
	@Id @GeneratedValue
	private long id;
	
	String frase;
	Integer cantDeResultados;
	long tiempoDeBusqueda;
	@Convert(converter=LocalDateConverter.class)
	LocalDate fecha;
	
	public void setFrase(String frase){
		this.frase = frase;
	}
	
	public void setCantDeResultados(Integer cantDeResultados){
		this.cantDeResultados = cantDeResultados;
	}
	
	public void setTiempoDeBusqueda(long tiempoDeBusqueda){
		this.tiempoDeBusqueda = tiempoDeBusqueda;
	}
	
	
	
	public String getFrase(){
		return this.frase;
	}
	
	public Integer getCantDeResultados(){
		return this.cantDeResultados;
	}
	
	public long getTiempoDeBusqueda(){
		return this.tiempoDeBusqueda;
	}
	
	public LocalDate getFecha(){
		return fecha;
	}
	
	public BusquedaHecha datosDeLaBusqueda(String unTextoLibre,List<POI> poisEncontrados){
	long tiempoInicio;
	tiempoInicio = System.nanoTime(); 
	cantDeResultados = poisEncontrados.size();
	tiempoDeBusqueda = System.nanoTime() - tiempoInicio;
	this.setFrase(unTextoLibre);
	this.setCantDeResultados(cantDeResultados);
	this.setTiempoDeBusqueda(tiempoDeBusqueda);
	this.setFecha(LocalDate.now());
	return this;
	}

	private void setFecha(LocalDate unaFecha) {
		this.fecha = unaFecha;
	}
	
	public boolean equals(BusquedaHecha otraBusqueda){
		boolean mismaFrase = Comparador.mismoString(frase,otraBusqueda.frase);
		boolean mismaCantidadResultados = Comparador.mismoNumero(cantDeResultados,otraBusqueda.cantDeResultados);
		boolean mismoTiempoBusqueda = Comparador.mismoNumero(tiempoDeBusqueda, otraBusqueda.tiempoDeBusqueda);
		boolean mismaFecha = (fecha.isEqual(otraBusqueda.fecha));
		return mismaFrase&&mismaCantidadResultados&&mismoTiempoBusqueda&&mismaFecha;
	}
}
