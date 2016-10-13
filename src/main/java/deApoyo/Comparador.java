package deApoyo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import acciones.Accion;
import ar.edu.TPPOI.BusquedaHecha;
import ar.edu.TPPOI.Terminal;
import pois.CGP;
import pois.Direccion;
import pois.Horario;
import pois.LocalComercial;
import pois.POI;
import pois.ParadaDeColectivo;
import pois.SucursalBanco;

public class Comparador {
	
	//POIS
	
	public static boolean mismoPOI(POI unPOI, POI otroPOI){
		boolean mismoPOI;
		boolean mismoTipo;
		boolean mismoComun;
		mismoTipo = (unPOI.soyElMismoPOI(otroPOI));
		if (mismoTipo){
			mismoComun = mismoComun(unPOI, otroPOI);
			if (mismoComun){
				if (unPOI.getClass()==ParadaDeColectivo.class){
					mismoPOI = true;
				} else if (unPOI.getClass()==LocalComercial.class){
					mismoPOI = mismoLocalComercial(unPOI, otroPOI);
				} else if (unPOI.getClass()==CGP.class){
					mismoPOI = mismoCGP(unPOI, otroPOI);
				} else if (unPOI.getClass()==SucursalBanco.class){
					mismoPOI = mismaSucursalBanco(unPOI, otroPOI);
				} else {
					mismoPOI = true;
				}
			} else {
				mismoPOI = false;
			}
		} else {
			mismoPOI = false;
		}
		return mismoPOI;
	}
	
	public static boolean mismoComun(POI unPOI, POI otroPOI){
		boolean mismaCoordenada = mismoPunto(unPOI.getCoordenada(), otroPOI.getCoordenada());
		boolean mismaDireccion = mismaDireccion(unPOI.getDireccion(),otroPOI.getDireccion());
		boolean mismoNombre = unPOI.getNombre().equalsIgnoreCase(otroPOI.getNombre());
		boolean mismoRadioCercania = mismoNumero(unPOI.getRadioCercania(), otroPOI.getRadioCercania());
		boolean mismoRubro = unPOI.getRubro().equalsIgnoreCase(otroPOI.getRubro());
		boolean mismosTags = Comparador.mismosElementos(unPOI.getTags(), otroPOI.getTags());
		return mismaCoordenada && mismaDireccion && mismoNombre && 
				mismoRadioCercania && mismoRubro && mismosTags; 
	}
	
	public static boolean mismaSucursalBanco(POI unBanco, POI otroBanco){
		SucursalBanco banco1, banco2;
		banco1 = (SucursalBanco) unBanco;
		banco2 = (SucursalBanco) otroBanco;
		return mismoString(banco1.getNombreSucursal(), banco2.getNombreSucursal());
	}
	
	public static boolean mismoLocalComercial(POI unLocal, POI otroLocal){
		LocalComercial local1 ,local2;
		local1 = (LocalComercial) unLocal;
		local2 = (LocalComercial) otroLocal;
		boolean mismosHorarios = mismosHorarios(local1.getHorarios(), local2.getHorarios());
		return mismosHorarios;
	}
	
	public static boolean mismoCGP(POI unCGP, POI otroCGP){
		CGP cgp1, cgp2;
		cgp1 = (CGP) unCGP;
		cgp2 = (CGP) otroCGP;
		boolean mismoPolig = mismaComuna(cgp1.getComuna(), cgp2.getComuna());
		boolean mismasZonas = Comparador.mismosElementos(cgp1.getZonasQueIncluye(), cgp2.getZonasQueIncluye());
		return mismoPolig && mismasZonas;
	}
	
	public static boolean mismaComuna(Comuna unaComuna, Comuna otraComuna){
		return unaComuna.equals(otraComuna);
	}
		
	public static boolean mismoPunto(Punto unPunto, Punto otroPunto){
		Double la1, la2, lo1, lo2;
		boolean mismaCoordenada;
		if (unPunto==null && otroPunto==null){
			mismaCoordenada = true;
		} else if (unPunto!=null && otroPunto!=null) {
			la1 = unPunto.latitude();
			la2 = otroPunto.latitude();
			lo1 = unPunto.longitude();
			lo2 = otroPunto.longitude();
			mismaCoordenada = (la1.longValue()==la2.longValue()) && (lo1.longValue()==lo2.longValue());
		} else {
			mismaCoordenada = false;
		}
		return mismaCoordenada;
	}
	
	public static boolean mismaDireccion(Direccion unaDireccion, Direccion otraDireccion){
		boolean mismoBarrio = mismoString(
				unaDireccion.getBarrio(), otraDireccion.getBarrio());;
		boolean mismaCalle1 = mismoString(
				unaDireccion.getCalle1(), otraDireccion.getCalle1());;
		boolean mismaCalle2 = mismoString(
				unaDireccion.getCalle2(), otraDireccion.getCalle2());;
		boolean mismaCallePrincipal = mismoString(
				unaDireccion.getCallePrincipal(), otraDireccion.getCallePrincipal());
		boolean mismaLetraDepto = mismoString(
				unaDireccion.getLetraDepto(), otraDireccion.getLetraDepto());;
		boolean mismaLocalidad = mismoString(
				unaDireccion.getLocalidad(), otraDireccion.getLocalidad());;
		boolean mismoNumero = mismoNumero(unaDireccion.getNumero(),otraDireccion.getNumero());
		boolean mismoPiso = (unaDireccion.getPiso()==otraDireccion.getPiso());
		boolean mismoPais = mismoString(
				unaDireccion.getPais(), otraDireccion.getPais());;
		boolean mismaProvincia = mismoString(
				unaDireccion.getProvincia(), otraDireccion.getProvincia());;
		boolean mismaUnidad = mismoString(
				unaDireccion.getUnidad(), otraDireccion.getUnidad());;
		boolean mismaDireccion = mismaCalle1&&mismaCalle2&&mismaCallePrincipal&&mismaLetraDepto&&mismaLocalidad&&
				mismaProvincia&&mismaUnidad&&mismoBarrio&&mismoNumero&&mismoNumero&&mismoPais&&mismoPiso;
		return mismaDireccion;
	}
	
	//TERMINALES
	
	public static boolean mismaTerminal(Terminal unaTerminal, Terminal otraTerminal){
		boolean mismoString = (unaTerminal.getDescripcion().equals(otraTerminal.getDescripcion()));
		boolean mismaComuna = (unaTerminal.getComuna().equals(otraTerminal.getComuna()));
		boolean mismasBusquedas = Comparador.mismasBusquedas(unaTerminal.getBusquedasHechas(),otraTerminal.getBusquedasHechas());
		boolean mismasAcciones = Comparador.mismasAcciones(unaTerminal.getAcciones(), otraTerminal.getAcciones());
		return mismaComuna&&mismasBusquedas&&mismasAcciones&&mismoString;
	}
	
	//GENERALES
	
	public static boolean mismasAcciones(Set<Accion> set, Set<Accion> set2){
		boolean mismoTamanio = (set.size()==set2.size());
		if (mismoTamanio){
			return set.stream().allMatch(
				aElem->set2.stream().anyMatch(
					otherElem->aElem.equals(otherElem)));
		} else {
			return false;
		}
	}
	
	public static boolean mismasBusquedas(List<BusquedaHecha> unaLista, List<BusquedaHecha> otraLista){
		boolean mismoTamanio = (unaLista.size()==otraLista.size());
		if (mismoTamanio){
			return unaLista.stream().allMatch(
				aElem->otraLista.stream().anyMatch(
					otherElem->aElem.equals(otherElem)));
		} else {
			return false;
		}
	}
	
	public static boolean mismosHorarios(List<Horario> unaLista, List<Horario> otraLista){
		boolean mismoTamanio = (unaLista.size()==otraLista.size());
		if (mismoTamanio){
			return unaLista.stream().allMatch(
				unHorario->otraLista.stream().anyMatch(
					otroHorario->unHorario.equals(otroHorario)));
		} else {
			return false;
		}
	}
	
	public static <T> boolean mismosElementos(Collection<T> aCollection, Collection<T> otherCollection){
		ArrayList<T> unaLista = new ArrayList<T>();
		ArrayList<T> otraLista = new ArrayList<>();
		unaLista.addAll(aCollection);
		otraLista.addAll(otherCollection);
		boolean mismoTamanio = (unaLista.size()==otraLista.size());
		boolean mismosElementos = unaLista.stream().allMatch(
			unElemento->otraLista.stream().anyMatch(
				otroElemento->unElemento.equals(otroElemento)));
		return mismoTamanio && mismosElementos;
	}

	public static boolean mismoString(String unString, String otroString){
		boolean sonIguales;
		if (unString==null && otroString==null){
			sonIguales = true;
		} else if (unString!=null && otroString!=null){
			sonIguales = unString.equalsIgnoreCase(otroString);
		} else {
			sonIguales = false;
		}
		return sonIguales;
	}
	
	public static boolean mismoNumero(Number unNumero, Number otroNumero){
		boolean sonIguales;
		if (unNumero==null && otroNumero==null){
			sonIguales = true;
		} else if (unNumero!=null && otroNumero!=null){
			sonIguales = mismoString(unNumero.toString(), otroNumero.toString());
		} else {
			sonIguales = false;
		}
		return sonIguales;
	}

}
