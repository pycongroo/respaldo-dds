package pois;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import deApoyo.Punto;

@Entity
public class ParadaDeColectivo extends POI {
	
	@SuppressWarnings(value="unused")
	public ParadaDeColectivo(){}

	public ParadaDeColectivo(String unNombre, Punto unaCoordenada, Direccion unaDireccion) {
		this(unNombre, unaCoordenada, 100, unaDireccion);
	}

	public ParadaDeColectivo(String unNombre, Punto unaCoordenada, Integer unRadioCercania, Direccion unaDireccion) {
		this.nombre = unNombre;
		this.setCoordenada(unaCoordenada);
		this.radioCercania = unRadioCercania;
		this.direccion = unaDireccion;
		this.rubro = "Transporte";
	}

	public boolean estaDisponible(LocalDateTime unMomento) {
		return true;
	}

	public boolean coincideConAtributo(String unTextoLibre) {
		return this.nombre.equals(unTextoLibre);
	}

	public void actualizar(POI unPOI) {
		this.actualizarDesdeDatos(unPOI.getCoordenada(), unPOI.getRadioCercania(), unPOI.getRubro(),
				unPOI.getDireccion(), unPOI.getTags());
	}

}