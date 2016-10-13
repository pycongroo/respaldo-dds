package deApoyo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbar.geodds.Point;

@Entity
@Table(name="Puntos")
public class Punto {
	
	@Id @GeneratedValue
	private long id;
	
	private double latitud;
	private double longitud;
	
	public Punto(){}
	
	public Punto(double latitud, double longitud){
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
	public double latitude(){
		return latitud;
	}
	
	public double longitude(){
		return longitud;
	}
	
	public double distance(Punto otroPunto){
		return getPoint().distance(new Point(otroPunto.latitud, otroPunto.longitud));
	}
	
	private Point getPoint(){
		return new Point(latitud, longitud);
	}
	
	public String toString(){
		return getPoint().toString();
	}
	
	public boolean equals(Punto otroPunto){
		return (latitud==otroPunto.latitud)&&(longitud==otroPunto.longitud);
	}

}
