package acciones;

import javax.persistence.Entity;
import javax.persistence.Transient;

import ar.edu.TPPOI.BusquedaHecha;
import ar.edu.TPPOI.Terminal;
import manejoErrores.EnvioDeMail;

@Entity
public class Notificar extends Accion {

	long tiempoLimite;
	
	@Transient
	EnvioDeMail envioDeMail;
	
	public Notificar(){}

	public void ejecutarLuegoDeLaBusqueda(BusquedaHecha unaBusqueda, Terminal unaTerminal) {

		if (unaBusqueda.getTiempoDeBusqueda() > tiempoLimite) {
			envioDeMail.setTiempoLimite(tiempoLimite);
			envioDeMail.setTiempoBusqueda(unaBusqueda.getTiempoDeBusqueda());
			envioDeMail.enviarMail();
		}
	}

	public void setTiempoLimite(long unTiempoLimite) {
		this.tiempoLimite = unTiempoLimite;
	}

	public Notificar(EnvioDeMail unEnvioDeMail) {
		this.envioDeMail = unEnvioDeMail;
	}
	
	public boolean equals(Notificar otroNotificar){
//		return (tiempoLimite==otroNotificar.tiempoLimite)&&(envioDeMail.equals(otroNotificar.envioDeMail));
		return true;
	}

}
