package pois;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import converters.LocalTimeConverter;

@Entity
@Table(name="Horarios")
public class Horario {
	
	@Id @GeneratedValue
	private long id;
	
	private DayOfWeek diaDeLaSemana;
	@Convert(converter=LocalTimeConverter.class)
	private LocalTime horaInicio;
	@Convert(converter=LocalTimeConverter.class)
	private LocalTime horaFin;
	
	@SuppressWarnings(value="unused")
	public Horario(){}

	public Horario(DayOfWeek unDiaDeLaSemana, LocalTime unaHoraInicio, LocalTime unaHoraFin) {
		this.diaDeLaSemana = unDiaDeLaSemana;
		this.horaInicio = unaHoraInicio;
		this.horaFin = unaHoraFin;
	}

	public boolean estaEnMiHorario(LocalDateTime unaFechaHora) {
		DayOfWeek unDiaDeSemana = unaFechaHora.toLocalDate().getDayOfWeek();
		LocalTime horaDelDia = unaFechaHora.toLocalTime();

		return ((this.diaDeLaSemana == unDiaDeSemana)
				&& (horaDelDia.isAfter(horaInicio) && horaDelDia.isBefore(horaFin)));
	}
	
	public boolean equals(Horario otroHorario){
		boolean mismoDia = (diaDeLaSemana==otroHorario.diaDeLaSemana);
		boolean mismaHoraInicio = (horaInicio == otroHorario.horaInicio);
		boolean mismaHoraFin = (horaFin == otroHorario.horaFin);
		return mismoDia&&mismaHoraInicio&&mismaHoraFin;
	}

}