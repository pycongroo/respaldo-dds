package deApoyo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

@Entity
@Table(name="Comunas")
public class Comuna {
	


	@Id @GeneratedValue
	private long id;
	
	private String descripcion;

	@OneToMany(cascade=CascadeType.ALL) @JoinColumn(name="Comuna_id")
	List<Punto> puntos;
	
	public Comuna(){
		puntos = new ArrayList<Punto>();
	}

	public Comuna(List<Punto> unosPuntos) {
		this.puntos = unosPuntos;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void add(Punto unPunto){
		this.puntos.add(unPunto);
	}
	
	public boolean isInside(Punto unPunto){
		return getPoligon().isInside(new Point(unPunto.latitude(), unPunto.longitude()));
	}
	
	public Polygon getPoligon(){
		Polygon polygon = new Polygon();
		puntos.forEach(unPunto->polygon.add(
			new Point(unPunto.latitude(), unPunto.longitude())));
		return polygon;
	}
	
	public boolean equals(Comuna otraComuna){
		boolean mismosPuntos = Comparador.mismosElementos(puntos, otraComuna.puntos);
		return mismosPuntos;
	}
	
}
