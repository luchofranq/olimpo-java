package olimpo;

import java.time.LocalDate;
import java.util.ArrayList;

public class CCDTyE {
	private String nombre;
	private String ubicacion;
	private LocalDate fechaInicio;
	private LocalDate fechaCierre;
	public final static int POLICIA = 0;
	public final static int GENDARMERIA = 1;
	public final static int EJERCITO = 2;
	private boolean[] responsable = new boolean[3];

	public CCDTyE(String nombre, String ubicacion, LocalDate fechaInicio, LocalDate fechaFIn, boolean[] fuerzas) {
		// TODO Auto-generated constructor stub
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.fechaInicio = fechaInicio;
		this.fechaCierre = fechaFIn;
		this.responsable = fuerzas;

	}

	public CCDTyE(String nombreCentro) {
		this.nombre = nombreCentro;
	}

	public ArrayList<String> getFuerzas() {
		ArrayList<String> fuerzas = new ArrayList<String>();
		if (responsable[POLICIA]) {
			fuerzas.add("Policia");
		}
		if (responsable[GENDARMERIA]) {
			fuerzas.add("Gendarmeria");
		}
		if (responsable[EJERCITO]) {
			fuerzas.add("Ejercito");
		}

		return fuerzas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDate fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public boolean[] getResponsable() {
		return responsable;
	}

	public void setResponsable(boolean[] responsable) {
		this.responsable = responsable;
	}

}
