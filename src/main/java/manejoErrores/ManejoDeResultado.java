package manejoErrores;

import procesos.Proceso;

public abstract class ManejoDeResultado {


	public abstract void ejecutarEnCasoDeFalla(Proceso unProceso);

	

	public boolean noAceptaCombinarManejos() {
		return false;
	};
}
