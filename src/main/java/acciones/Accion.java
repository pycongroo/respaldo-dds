package acciones;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import ar.edu.TPPOI.BusquedaHecha;
import ar.edu.TPPOI.Terminal;

@Entity
@Table(name="Acciones")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class Accion {
	
	@Id @GeneratedValue
	private long id;
	
	public abstract void ejecutarLuegoDeLaBusqueda(BusquedaHecha unaBusqueda, Terminal terminal);
}
