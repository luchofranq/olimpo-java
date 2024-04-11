package olimpo;

public class Testigo {
	private String nombreCompleto;
	private int DNI;
	private String testimonio;

	public Testigo(String nombre, int dni, String testimonio) {
		this.nombreCompleto = nombre;
		this.DNI = dni;
		this.testimonio = testimonio;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getTestimonio() {
		return testimonio;
	}

	public void setTestimonio(String testimonio) {
		this.testimonio = testimonio;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

}
