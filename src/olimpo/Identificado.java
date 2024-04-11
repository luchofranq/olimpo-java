package olimpo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Identificado extends Detenido {
	private String nombreCompleto;
	private int DNI;
	private String lugarSecuestro;
	private LocalDate ultVezVisto;
	private String biografia;
	private String material;
	private String profesion;

	public Identificado(String nombreCompleto, int DNI, String lugarSecuestro, LocalDate ultVezVisto, String biografia,
			String material, ArrayList<CCDTyE> centros, Testigo testigoAsociado, String profesion) {
		this.nombreCompleto = nombreCompleto;
		this.DNI = DNI;
		this.lugarSecuestro = lugarSecuestro;
		this.ultVezVisto = ultVezVisto;
		this.biografia = biografia;
		this.material = material;
		this.setCentros(centros);
		this.setTestigoAsociado(testigoAsociado);
		this.profesion = profesion;
	}

	public int tiempoCautivo() {
		int ultimapos = getCentros().size() - 1;
		CCDTyE ultimocentro = getCentros().get(ultimapos);
		Period tiempoCautivo = Period.between(ultimocentro.getFechaCierre(), getUltVezVisto());
		int days = Math.abs(tiempoCautivo.getDays());
		return days;
	}

	public boolean sobrevivio() {
		if (getTestigoAsociado() == null) {
			return false;
		} else
			return true;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getLugarSecuestro() {
		return lugarSecuestro;
	}

	public void setLugarSecuestro(String lugarSecuestro) {
		this.lugarSecuestro = lugarSecuestro;
	}

	public LocalDate getUltVezVisto() {
		return ultVezVisto;
	}

	public void setUltVezVisto(LocalDate ultVezVisto) {
		this.ultVezVisto = ultVezVisto;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

}
