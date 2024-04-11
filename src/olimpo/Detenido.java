package olimpo;

import java.util.ArrayList;

public abstract class Detenido {
	private Testigo testigoAsociado;
	private ArrayList<CCDTyE> centros = new ArrayList<>();

	public void agregarCentro(CCDTyE centro) {
		centros.add(centro);
	}

	public Testigo getTestigoAsociado() {
		return testigoAsociado;
	}

	public void setTestigoAsociado(Testigo testigoAsociado) {
		this.testigoAsociado = testigoAsociado;
	}

	public ArrayList<CCDTyE> getCentros() {
		return centros;
	}

	public void setCentros(ArrayList<CCDTyE> centros) {
		this.centros = centros;
	}

	public abstract int tiempoCautivo();

}
