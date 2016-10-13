package clasesParaTests;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import manejoErrores.EnvioDeMail;

public class EnvioMailImpostor extends EnvioDeMail {
	
	protected void generateAndSendEmail(long unTiempoLimite, long unTiempoDeBusqueda)
			throws AddressException, MessagingException {
		//no hace nada
	}
}
