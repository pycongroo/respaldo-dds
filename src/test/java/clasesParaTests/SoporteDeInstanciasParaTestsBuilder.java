package clasesParaTests;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import acciones.Almacenar;
import acciones.Notificar;
import ar.edu.TPPOI.MapaPOI;
import ar.edu.TPPOI.ServicioBajaPOIs;
import ar.edu.TPPOI.Terminal;
import criteriosFiltradoTerminales.ComunaALaQuePertenece;
import criteriosFiltradoTerminales.TodosLosUsuarios;
import criteriosFiltradoTerminales.UsuariosElegidosPorAdmin;
import deApoyo.ConfiguradorDeProcesos;
import deApoyo.GeneradorDeReportes;
import deApoyo.Comuna;
import deApoyo.Punto;
import deApoyo.RepositorioDeTerminales;
import externos.BancoAdapter;
import externos.CGPAdapter;
import externos.CentroDTO;
import externos.ServicioDTO;
import manejoErrores.EnvioDeMail;
import manejoErrores.NoRealizarAccion;
import manejoErrores.ReintentarNVeces;
import pois.CGP;
import pois.Direccion;
import pois.Horario;
import pois.LocalComercial;
import pois.ParadaDeColectivo;
import pois.Servicio;
import pois.SucursalBanco;
import procesos.ProcActualizarLocalesComerciales;
import procesos.ProcAgregarAccionesParaUsuarios;
import procesos.ProcDarDeBajaPOIs;
import procesos.ProcQuitarAccionesParaUsuarios;


public class SoporteDeInstanciasParaTestsBuilder {

	Punto miCoordenaAbasto, coordenadaCercaParada114, coordenadaCercaBancoCiudad, coordenadaStarbucks,
			coordenadaCercaStarbucks, coordenadaSportClub, coordenadaCineAbasto, coordenadaParada114,
			coordenadaBancoCiudad;
	ParadaDeColectivo parada114DeCabildoYMonroe, parada114DeLugano;
	Servicio cargaSUBE, prestamo, cortePelo, deudas;
	LocalComercial starbucksCoronelDiaz1400, sportClubLibertador7395, cineAbasto, starbucksRivadavia;
	BancoExternoParaTest bancoExternoImpostor;
	BancoAdapter bancoAdapter;
	CGPExternoParaTest cgpExternoImpostor;
	CGPAdapter cgpAdapter;
	SucursalBanco bancoCiudadCabildoYCongreso;
	CGP cgpComuna5;
	MapaPOI mapa;
	Terminal terminal;
	Notificar notificar;
	Almacenar almacenar;
	EnvioDeMail envioDeMail;
	GeneradorDeReportes generadorReportes;
	ProcActualizarLocalesComerciales actualizadorDeLocalesComerciales;
	ProcDarDeBajaPOIs procesoBajaDePOIs;
	ConfiguradorDeProcesos configuradorDeProcesos;
	ServicioBajaPOIs servicioBajaDePOIs;
	ReintentarNVeces reintentarNVeces;
	NoRealizarAccion noRealizarAccion;
	Date generarHorario;
	RepositorioDeTerminales rep;
	ProcAgregarAccionesParaUsuarios procAgregarAcciones;
	ProcQuitarAccionesParaUsuarios procQuitarAcciones;
	ComunaALaQuePertenece comunaCriterio;
	TodosLosUsuarios todosUsersCriterio;
	Comuna comunaAbasto;
	Comuna comunaCaballito;
	UsuariosElegidosPorAdmin adminCriterio;
	

	public Punto miCoordenaAbasto() {
		if (miCoordenaAbasto == null) {
			miCoordenaAbasto = new Punto(-58.42059446334839, -34.60421247366349);
		}

		return miCoordenaAbasto;
	}

	public ParadaDeColectivo paradaDeColectivo114DeCabildoYMonroe() {
		if (parada114DeCabildoYMonroe == null) {
			coordenadaParada114 = new Punto(-58.459845185279846, -34.558164509672146);
			Direccion direccionParada114 = new Direccion();
			direccionParada114.setCalles("Monroe", "Cabildo");
			parada114DeCabildoYMonroe = new ParadaDeColectivo("114", coordenadaParada114, direccionParada114);
		}

		return parada114DeCabildoYMonroe;
	}

	public ParadaDeColectivo paradaDeColectivo114DeLugano() {
		if (parada114DeLugano == null) {
			coordenadaParada114 = new Punto(-88.459845185279846, -24.558164509672146);
			Direccion direccionParada114 = new Direccion();
			direccionParada114.setCalles("lugano", "mozart");
			parada114DeLugano = new ParadaDeColectivo("114", coordenadaParada114, direccionParada114);
		}

		return parada114DeLugano;
	}

	public Servicio prestamo() {
		if (prestamo == null) {
			prestamo = Servicio.nuevoServicioBanco("prestamo");
		}

		return prestamo;

	}

	public Servicio deudas() {
		if (deudas == null) {
			deudas = Servicio.nuevoServicioBanco("deudas");
		}

		return deudas;

	}

	public Servicio cargaSUBE() {
		if (cargaSUBE == null) {
			List<Horario> horarios = new ArrayList<>();
			horarios.add(new Horario(DayOfWeek.FRIDAY, LocalTime.of(8, 00), LocalTime.of(13, 00)));
			horarios.add(new Horario(DayOfWeek.FRIDAY, LocalTime.of(15, 00), LocalTime.of(20, 00)));
			cargaSUBE = new Servicio("cargar SUBE", horarios);
		}

		return cargaSUBE;

	}

	public String json() {
		return "[{ 'banco': 'Banco de la Plaza'," + "'x': -35.9338322," + "'y': 72.348353,"
				+ "'sucursal': 'Avellaneda'," + "'gerente': 'Javier Loeschbor',"
				+ "'servicios': [ 'cobro cheques', 'depósitos', 'extracciones', 'transferencias', 'créditos', '', '', '' ]"
				+ "}," + "{  'banco': 'Banco de la Plaza'," + "'x': -35.9345681," + "'y': 72.344546,"
				+ "'sucursal': 'Caballito'," + "'gerente': 'Fabián Fantaguzzi',"
				+ "'servicios': [ 'depósitos', 'extracciones', 'transferencias', 'seguros', '', '', '', '' ]" + "}"
				+ "]";
	}

	public BancoAdapter bancoAdapter() {
		bancoAdapter = new BancoAdapter(new BancoExternoParaTest());
		return bancoAdapter;

	}

	public CentroDTO crearCentroDTO() {
		CentroDTO nuevoCGP = new CentroDTO();
		nuevoCGP.director = "Juan";
		nuevoCGP.domicilioCompleto = "Junin 521";
		nuevoCGP.zonas.add("San Cristobal");
		nuevoCGP.zonas.add("Balvanera");
		nuevoCGP.numeroDeComuna = 3;
		nuevoCGP.telefono = "4375-0644/45";
		nuevoCGP.serviciosDTO = crearServiciosDTO();
		return nuevoCGP;
	}

	public ServicioDTO[] crearServiciosDTO() {
		ServicioDTO unServicio = new ServicioDTO();
		unServicio.setNombreServicio("asesoramiento");
		List<Integer[]> rangosServicio = new ArrayList<>();
		rangosServicio.add(new Integer[] { 1, 9, 0, 14, 0 });
		rangosServicio.add(new Integer[] { 2, 9, 0, 14, 0 });
		rangosServicio.add(new Integer[] { 3, 9, 0, 14, 0 });
		rangosServicio.add(new Integer[] { 4, 9, 0, 14, 0 });
		rangosServicio.add(new Integer[] { 5, 9, 0, 14, 0 });
		rangosServicio.add(new Integer[] { 6, 10, 0, 13, 0 });
		unServicio.setRangoServicioDTO(rangosServicio);
		ServicioDTO[] serviciosDTO = new ServicioDTO[] { unServicio };
		return serviciosDTO;
	}

	public CGPAdapter CGPAdapter() {
		cgpAdapter = new CGPAdapter(new CGPExternoParaTest());
		return cgpAdapter;

	}

	public SucursalBanco bancoCiudadCabildoYCongreso() {
		if (bancoCiudadCabildoYCongreso == null) {
			coordenadaBancoCiudad = new Punto(-58.46362049999999, -34.5545459);
			Direccion direccionBancoCiudad = new Direccion();
			direccionBancoCiudad.setCalles("Cabildo", "Congreso");
			bancoCiudadCabildoYCongreso = new SucursalBanco("Banco Ciudad", "Belgrano", coordenadaBancoCiudad,
					direccionBancoCiudad);
			bancoCiudadCabildoYCongreso.agregarServicio(this.prestamo());
		}

		return bancoCiudadCabildoYCongreso;
	}

	public CGP cgpComuna5() {
		if (cgpComuna5 == null) {
			Direccion direccionCGP = new Direccion("Corrientes", 500);
			List<Punto> puntos = new ArrayList<>();
			List<String> zonasCGP5 = new ArrayList<>();
			puntos.add(new Punto(-58.411898, -34.597984));
			puntos.add(new Punto(-58.426446, -34.597878));
			puntos.add(new Punto(-58.433334, -34.602696));
			puntos.add(new Punto(-58.430051, -34.615469));
			puntos.add(new Punto(-58.427899, -34.622162));
			puntos.add(new Punto(-58.412372, -34.620890));
			Comuna poligonoCGP = new Comuna(puntos);
			cgpComuna5 = new CGP("Comuna 5", "Propositos generales", poligonoCGP, direccionCGP);
			cgpComuna5.agregarServicio(cargaSUBE());
			zonasCGP5.add("Almagro");
			cgpComuna5.setZonasQueIncluye(zonasCGP5);
		}

		return cgpComuna5;
	}
	
	

	public LocalComercial starbucksCoronelDiaz1400() {
		if (starbucksCoronelDiaz1400 == null) {
			coordenadaStarbucks = new Punto(-58.413718, -34.593303);
			LocalTime horaInicio = LocalTime.of(10, 00);
			LocalTime horaFin = LocalTime.of(20, 00);
			List<Horario> horarios2 = new ArrayList<>();
			horarios2.add(new Horario(DayOfWeek.MONDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.TUESDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.WEDNESDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.THURSDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.FRIDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.SATURDAY, horaInicio, horaFin));
			Direccion direccionStarbucks = new Direccion("Coronel Diaz", 1400);
			starbucksCoronelDiaz1400 = LocalComercial.nuevoLocalConRubroCafeteria("Starbucks", coordenadaStarbucks,
					horarios2, direccionStarbucks);
		}

		return starbucksCoronelDiaz1400;
	}

	public LocalComercial starbucksRivadavia() {
		if (starbucksRivadavia == null) {
			coordenadaStarbucks = new Punto(-98.413718, -44.593303);
			LocalTime horaInicio = LocalTime.of(10, 00);
			LocalTime horaFin = LocalTime.of(20, 00);
			List<Horario> horarios2 = new ArrayList<>();
			horarios2.add(new Horario(DayOfWeek.MONDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.TUESDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.WEDNESDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.THURSDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.FRIDAY, horaInicio, horaFin));
			horarios2.add(new Horario(DayOfWeek.SATURDAY, horaInicio, horaFin));
			Direccion direccionStarbucks = new Direccion("Rivadavia", 3647);
			starbucksRivadavia = LocalComercial.nuevoLocalConRubroCafeteria("Starbucks"+ "", coordenadaStarbucks, horarios2,
					direccionStarbucks);
			starbucksRivadavia.setTag("cafe");
		}

		return starbucksRivadavia;
	}

	public LocalComercial sportClubLibertador7395() {
		if (sportClubLibertador7395 == null) {
			coordenadaSportClub = new Punto(-58.4627205, -34.5436991);
			LocalTime horaInicioGym = LocalTime.of(7, 00);
			LocalTime horaFinGym = LocalTime.of(22, 00);
			List<Horario> horariosGym = new ArrayList<>();
			horariosGym.add(new Horario(DayOfWeek.MONDAY, horaInicioGym, horaFinGym));
			horariosGym.add(new Horario(DayOfWeek.TUESDAY, horaInicioGym, horaFinGym));
			horariosGym.add(new Horario(DayOfWeek.WEDNESDAY, horaInicioGym, horaFinGym));
			horariosGym.add(new Horario(DayOfWeek.THURSDAY, horaInicioGym, horaFinGym));
			horariosGym.add(new Horario(DayOfWeek.FRIDAY, horaInicioGym, horaFinGym));
			horariosGym.add(new Horario(DayOfWeek.SATURDAY, horaInicioGym, horaFinGym));
			Direccion direccionSportClub = new Direccion("Avenida Libertador", 7395);
			sportClubLibertador7395 = LocalComercial.nuevoLocal("SportClub", coordenadaSportClub, 15, horariosGym,
					"Gimnasio", direccionSportClub);
			sportClubLibertador7395.setTag("fitness");
			sportClubLibertador7395.setTag("musculacion");
			sportClubLibertador7395.setTag("spinning");
		}

		return sportClubLibertador7395;
	}

	public LocalComercial cineAbasto() {
		if (cineAbasto == null) {
			coordenadaCineAbasto = new Punto(-34.6033055, -58.411887);
			Direccion direccionCineAbasto = new Direccion("Av Corrientes", 3247);
			LocalTime horaInicioCine = LocalTime.of(10, 00);
			LocalTime horaFinCine = LocalTime.of(23, 00);
			List<Horario> horariosCine = new ArrayList<>();
			horariosCine.add(new Horario(DayOfWeek.MONDAY, horaInicioCine, horaFinCine));
			horariosCine.add(new Horario(DayOfWeek.TUESDAY, horaInicioCine, horaFinCine));
			horariosCine.add(new Horario(DayOfWeek.WEDNESDAY, horaInicioCine, horaFinCine));
			horariosCine.add(new Horario(DayOfWeek.THURSDAY, horaInicioCine, horaFinCine));
			horariosCine.add(new Horario(DayOfWeek.FRIDAY, horaInicioCine, horaFinCine));
			horariosCine.add(new Horario(DayOfWeek.SATURDAY, horaInicioCine, horaFinCine));
			horariosCine.add(new Horario(DayOfWeek.SUNDAY, horaInicioCine, horaFinCine));
			cineAbasto = new LocalComercial("cine Abasto", coordenadaCineAbasto, 800, horariosCine, "cine",
					direccionCineAbasto);
			cineAbasto.setTag("cine");
		}

		return cineAbasto;
	}

	public MapaPOI mapa() {
		if (mapa == null) {
			mapa = new MapaPOI();
			mapa.agregarPOI(paradaDeColectivo114DeCabildoYMonroe());
			mapa.agregarPOI(paradaDeColectivo114DeLugano());
			mapa.agregarPOI(bancoCiudadCabildoYCongreso());
			mapa.agregarPOI(cgpComuna5());
			mapa.agregarPOI(starbucksRivadavia());
			mapa.agregarPOI(starbucksCoronelDiaz1400());
			mapa.agregarPOI(sportClubLibertador7395());
			mapa.agregarPOI(cineAbasto());
			mapa.agregarSistemaExternoAdapter(bancoAdapter());
			mapa.agregarSistemaExternoAdapter(CGPAdapter());
		}

		return mapa;
	}
	
	
	
	public Terminal terminal(){
	terminal= new Terminal();
	return terminal;
	
	}
	
	
	
	public ProcAgregarAccionesParaUsuarios procAgregarAcciones(){
		return new ProcAgregarAccionesParaUsuarios();
	}
	
	public ProcQuitarAccionesParaUsuarios procQuitarAcciones(){
		return new ProcQuitarAccionesParaUsuarios();
	}
	
	public GeneradorDeReportes reportes(){
		return new GeneradorDeReportes();
	}

	public ComunaALaQuePertenece comunaCriterio(){
		return new ComunaALaQuePertenece();
	}

	public TodosLosUsuarios todosUsersCriterio(){
		return new TodosLosUsuarios();
	}
	
	public UsuariosElegidosPorAdmin adminCriterio(){
		return new UsuariosElegidosPorAdmin();
	}

	public EnvioDeMail envioDeMail() {
		if (envioDeMail == null) {
			envioDeMail = new EnvioDeMail();
		}

		return envioDeMail;
	}

	public Notificar notificar(EnvioDeMail unEnvioDeMail) {
		if (notificar == null) {
			notificar = new Notificar(unEnvioDeMail);
		}

		return notificar;
	}

	public Almacenar almacenar() {
		if (almacenar == null) {
			almacenar = new Almacenar();
		}

		return almacenar;
	}

	public ProcActualizarLocalesComerciales actualizadorDeLocalesComerciales() {
		if (actualizadorDeLocalesComerciales==null){
			actualizadorDeLocalesComerciales=new ProcActualizarLocalesComerciales();
		}
		return actualizadorDeLocalesComerciales;
	}
	
	public ProcDarDeBajaPOIs procesoBajaDePOIs(){
		if (procesoBajaDePOIs==null){
			procesoBajaDePOIs=new ProcDarDeBajaPOIs();
		}
		return procesoBajaDePOIs;
	}
	
	public ServicioBajaPOIs servicioBajaDePOIs(){
		if (servicioBajaDePOIs==null){
			servicioBajaDePOIs=new ServicioBajaPOIs();
		}
		return servicioBajaDePOIs;
	}

	public ConfiguradorDeProcesos configuradorDeProcesos() {
		if (configuradorDeProcesos==null){
			configuradorDeProcesos=new ConfiguradorDeProcesos();
		}
			
		return configuradorDeProcesos;
	}

	public ReintentarNVeces reintentarNVeces() {
		if (reintentarNVeces==null){
			reintentarNVeces= new ReintentarNVeces();
		}
		return reintentarNVeces;
	}

	public NoRealizarAccion noRealizarAccion() {
		if(noRealizarAccion==null){
			noRealizarAccion=new NoRealizarAccion();
		}
		return noRealizarAccion;
	}

	public Date generarHorario() {
		if (generarHorario==null){
			generarHorario=new Date();
		}
		return generarHorario;
	}
	
	public Comuna crearComunaAbasto(){
		List<Punto> puntosAbasto = new ArrayList<>();
		puntosAbasto.add(new Punto(-58.411898, -34.597984));
		puntosAbasto.add(new Punto(-58.426446, -34.597878));
		puntosAbasto.add(new Punto(-58.433334, -34.602696));
		puntosAbasto.add(new Punto(-58.430051, -34.615469));
		puntosAbasto.add(new Punto(-58.427899, -34.622162));
		puntosAbasto.add(new Punto(-58.412372, -34.620890));
		comunaAbasto = new Comuna(puntosAbasto);
		comunaAbasto.setDescripcion("Abasto");
		return comunaAbasto;
	}
	
	public Comuna crearComunaCaballito(){
		List<Punto> puntosCaballito = new ArrayList<>();
		puntosCaballito.add(new Punto(-58.411898, -34.597984));
		puntosCaballito.add(new Punto(-58.426446, -34.597878));
		puntosCaballito.add(new Punto(-58.433334, -34.602696));
		puntosCaballito.add(new Punto(-58.430051, -34.615469));
		puntosCaballito.add(new Punto(-58.427899, -34.622162));
		puntosCaballito.add(new Punto(-58.412372, -34.620891));
		comunaCaballito = new Comuna(puntosCaballito);
		comunaCaballito.setDescripcion("Caballito");
		return comunaCaballito;
	}
}