package pois;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import deApoyo.Punto;
import excepciones.NoExisteServicioAsociadoException;

@Entity
public abstract class EmpresaMultiServicios extends POI {
	
	@ManyToMany(cascade = CascadeType.ALL) @JoinTable(name="ServicioPorPoi")
	protected List<Servicio> servicios = new ArrayList<>();

	public boolean estaDisponible(LocalDateTime unMomento, Servicio unServicio) {

		if (unServicio != null) {
			if (this.servicios.contains(unServicio)) {
				return unServicio.disponibleEn(unMomento);
			} else {
				throw new NoExisteServicioAsociadoException(
						"No existe el servicio " + unServicio.getNombre() + " en " + this.getNombre());
			}
		} else {
			return this.servicios.stream().anyMatch(servicio -> servicio.disponibleEn(unMomento));
		}
	}

	public List<Servicio> getServicios() {
		return servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public void agregarServicio(Servicio unServicio) {
		this.servicios.add(unServicio);
	}

	public void actualizar(POI unPOIExterno) {
		this.actualizarDesdeDatos(unPOIExterno.getCoordenada(), unPOIExterno.getRadioCercania(),
				unPOIExterno.getRubro(), unPOIExterno.getDireccion(), ((SucursalBanco) unPOIExterno).getServicios(),
				unPOIExterno.getTags());
	}

	public void actualizarDesdeDatos(Punto unaCoordenada, Integer unRadioCercania, String unRubro,
			Direccion unaDireccion, List<Servicio> unosServicios, List<String> unosTags) {
		super.actualizarDesdeDatos(unaCoordenada, unRadioCercania, unRubro, unaDireccion, unosTags);
		this.servicios = unosServicios;
	}
}