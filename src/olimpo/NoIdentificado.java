package olimpo;

import java.time.Period;
import java.util.ArrayList;

public class NoIdentificado extends Detenido {
	private String apodo;
	private String descripciones;

	public NoIdentificado(String apodo, String descripciones, ArrayList<CCDTyE> centros, Testigo testigoAsociado) {
		this.apodo = apodo;
		this.descripciones = descripciones;
		this.setCentros(centros);
		this.setTestigoAsociado(testigoAsociado);
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public String getDescripciones() {
		return descripciones;
	}

	public void setDescripciones(String descripciones) {
		this.descripciones = descripciones;
	}

	public boolean sobrevivio() {
		return false;
	}

	public int tiempoCautivo() {
		int ultimapos = getCentros().size() - 1;
		CCDTyE ultimocentro = getCentros().get(ultimapos);
		Period tiempoCautivo = Period.between(ultimocentro.getFechaCierre(), ultimocentro.getFechaInicio());
		int days = Math.abs(tiempoCautivo.getDays());
		return days;
	}

}
