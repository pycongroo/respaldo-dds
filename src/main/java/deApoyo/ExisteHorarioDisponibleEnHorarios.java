package deApoyo;

import java.time.LocalDateTime;
import java.util.List;

import pois.Horario;

public class ExisteHorarioDisponibleEnHorarios {

	private List<Horario> horarios;
	private LocalDateTime momento;

	public ExisteHorarioDisponibleEnHorarios(List<Horario> unosHorarios, LocalDateTime unMomento) {
		this.horarios = unosHorarios;
		this.momento = unMomento;
	}

	public boolean validar() {
		return this.horarios.stream().anyMatch(horario -> horario.estaEnMiHorario(this.momento));
	}
}