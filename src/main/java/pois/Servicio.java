package pois;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import deApoyo.ExisteHorarioDisponibleEnHorarios;

import java.time.DayOfWeek;

@Entity
@Table(name="Servicios")
public class Servicio {
	
	@Id @GeneratedValue
	private long id;
	
	@OneToMany(cascade = CascadeType.ALL) @JoinColumn(name="servicios_id")
	private List<Horario> horarios = new ArrayList<Horario>();
	private String nombre;

	public Servicio(String nombre) {
		this.nombre = nombre;
	}

	public Servicio(String unNombre, Horario unHorario) {
		this.nombre = unNombre;
		this.agregarHorario(unHorario);
	}

	public void agregarHorario(Horario unHorario) {
		horarios.add(unHorario);
	}

	public static Servicio nuevoServicioBanco(String unNombre) {
		return new Servicio(unNombre, Servicio.horariosBanco());
	}

	public static List<Horario> horariosBanco() {
		LocalTime horaInicio = LocalTime.of(10, 00);
		LocalTime horaFin = LocalTime.of(16, 00);

		List<Horario> horarios = new ArrayList<>();
		horarios.add(new Horario(DayOfWeek.MONDAY, horaInicio, horaFin));
		horarios.add(new Horario(DayOfWeek.TUESDAY, horaInicio, horaFin));
		horarios.add(new Horario(DayOfWeek.WEDNESDAY, horaInicio, horaFin));
		horarios.add(new Horario(DayOfWeek.THURSDAY, horaInicio, horaFin));
		horarios.add(new Horario(DayOfWeek.FRIDAY, horaInicio, horaFin));

		return horarios;
	}

	public Servicio(String unNombre, List<Horario> unosHorarios) {
		this.nombre = unNombre;
		this.horarios.addAll(unosHorarios);
	}

	public String getNombre() {
		return nombre;
	}

	public boolean disponibleEn(LocalDateTime unMomento) {
		return (new ExisteHorarioDisponibleEnHorarios(this.horarios, unMomento)).validar();
	}

}