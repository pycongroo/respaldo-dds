package pois;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import deApoyo.Punto;

@Entity
@Table(name="Pois")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class POI {
	
	@Id @GeneratedValue
	public long id;
	
	protected String nombre;
	protected String rubro;
	protected Integer radioCercania;
	@OneToOne
	protected Punto coordenada;
	@OneToOne
	protected Direccion direccion;
	@ElementCollection @CollectionTable(name="Tags")
	protected List<String> tags = new ArrayList<>();

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getRubro() {
		return rubro;
	}

	public Integer getRadioCercania() {
		return radioCercania;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	protected void setCoordenada(Punto unaCoordenada){
		this.coordenada = unaCoordenada;
	}

	public Punto getCoordenada() {
		return coordenada;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public void setTag(String unTag) {
		this.tags.add(unTag);
	}

	public boolean sosValido() {
		return this.tengoNombre() && this.tengoCoordenada();
	}

	public boolean tengoNombre() {
		return this.getNombre() != null;
	}

	public boolean tengoCoordenada() {
		return this.getCoordenada() != null;
	}

	public boolean estasCercaDe(Punto unaCoordenada) {
		return this.estasAMenosDeXMetrosDe(this.radioCercania, unaCoordenada);
	}

	private boolean estasAMenosDeXMetrosDe(Integer unosMetros, Punto unaCoordenada) {
		return this.distanciaAUnaCoordenada(unaCoordenada) < (unosMetros / 1000.0);
	}

	private double distanciaAUnaCoordenada(Punto unaCoordenada) {
		return this.getCoordenada().distance(unaCoordenada);
	}

	public boolean estasAMenosDeXMetrosDe(Integer unosMetros, POI unPOI) {
		return this.estasAMenosDeXMetrosDe(unosMetros, unPOI.getCoordenada());
	}

	private boolean coincideConAlgunTag(String unTextoLibre) {
		return tags.stream().anyMatch(unTag -> unTag.equals(unTextoLibre));
	}

	public boolean busqueda(String unTextoLibre) {
		return busquedaConcreta(unTextoLibre);
	}

	private boolean busquedaConcreta(String unTextoLibre) {
		return this.coincideConAtributo(unTextoLibre) || this.coincideConAlgunTag(unTextoLibre);
	}

	protected boolean estanContenidos(String unaPalabraClave, String unAtributo) {
		return StringUtils.containsIgnoreCase(unaPalabraClave, unAtributo)
				|| StringUtils.containsIgnoreCase(unAtributo, unaPalabraClave);
	}

	public boolean esBanco() {
		return false;
	}

	public boolean esCGP() {
		return false;
	}

	public boolean soyElMismoPOI(POI otroPOI) {
		return StringUtils.containsIgnoreCase(otroPOI.getNombre(), this.getNombre());
	}
	
	public abstract boolean coincideConAtributo(String unTextoLibre);

	public abstract void actualizar(POI unPOIExterno);

	public void actualizarDesdeDatos(Punto unaCoordenada, Integer unRadioCercania, String unRubro,
			Direccion unaDireccion, List<String> unosTags) {
		this.setCoordenada(unaCoordenada);;
		this.radioCercania = unRadioCercania;
		this.rubro = unRubro;
		this.direccion = unaDireccion;
		this.tags = unosTags;
	}

}