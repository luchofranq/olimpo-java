package olimpo_DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import olimpo.CCDTyE;
import olimpo.Identificado;
import olimpo.Testigo;

public class Identificado_DAO {

	public void insertIdentificado(Identificado identificado, int dniTestigo) {
		Testigo_DAO tdao = new Testigo_DAO();
		CCDTyE_DAO cdao = new CCDTyE_DAO();
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			int idTestigo = tdao.selectIdTestigo(dniTestigo);
			int idProfesion = selectIdProfesion(identificado.getProfesion());

			PreparedStatement prepareStatement = conn.prepareStatement(
					"INSERT INTO `identificado` (`nombreIdentificado`, `DNIIdentificado`, `lugarSecuestroIdentificado`, `ultVezVistoIdentificado`, `biografiaIdentificado`, `materialIdentificado`, `idTestigo`,`idprofesion` ) VALUES (?, ?, ?, ?, ?, ?, ?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			prepareStatement.setString(1, identificado.getNombreCompleto());
			prepareStatement.setInt(2, identificado.getDNI());
			prepareStatement.setString(3, identificado.getLugarSecuestro());
			prepareStatement.setDate(4, Date.valueOf(identificado.getUltVezVisto()));
			prepareStatement.setString(5, identificado.getBiografia());
			prepareStatement.setString(6, identificado.getMaterial());
			prepareStatement.setInt(7, idTestigo);
			prepareStatement.setInt(8, idProfesion);

			prepareStatement.executeUpdate();

			ResultSet rs = prepareStatement.getGeneratedKeys();

			if (rs.next()) {

				ArrayList<CCDTyE> centros = identificado.getCentros();
				for (CCDTyE centro : centros) {
					insertCCDTyEAsociado(selectIdIdentificado(identificado.getDNI()),
							cdao.selectIdCcdtye(centro.getNombre()));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void insertCCDTyEAsociado(int idIdentificado, int idCcdtye) {

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn
					.prepareStatement("INSERT INTO `ccdtye_identificado` (`idIdentificado`, `idCcdtye`) VALUES (?, ?)");
			prepareStatement.setInt(1, idIdentificado);
			prepareStatement.setInt(2, idCcdtye);
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<Identificado> selectAllIdentificados() {
		String query = "SELECT * FROM `identificado`";
		ArrayList<Identificado> identificados = new ArrayList<>();
		Testigo_DAO tdao = new Testigo_DAO();
		CCDTyE_DAO cdao = new CCDTyE_DAO();
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {

				int idTestigo = rs.getInt("idTestigo");
				Testigo testigo = tdao.selectTestigo(idTestigo);
				int idProfesion = rs.getInt("idprofesion");
				String profesion = selectProfesion(idProfesion);

				int identificadoDNI = rs.getInt("DNIIdentificado");

				ArrayList<CCDTyE> centros = cdao.selectCCDTyEAsociadosAIdentificado(identificadoDNI);

				Identificado identificado = new Identificado(rs.getString("nombreIdentificado"), identificadoDNI,
						rs.getString("lugarSecuestroIdentificado"), rs.getDate("ultVezVistoIdentificado").toLocalDate(),
						rs.getString("biografiaIdentificado"), rs.getString("materialIdentificado"), centros, testigo,
						profesion);
				identificados.add(identificado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return identificados;
	}

	public void deleteIdentificado(int identificadoDNI) {

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		String deleteIdentificadoQuery = "DELETE FROM `identificado` WHERE `DNIIdentificado` = ?";
		String deleteIdentificadoCentroQuery = "DELETE FROM `ccdtye_identificado` WHERE `IdIdentificado` = ?";
		// TODO: BORRAR SEGUN ID IDENTIFICADO
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement deleteIdentificadoCentroStatement = conn.prepareStatement(deleteIdentificadoCentroQuery);
			deleteIdentificadoCentroStatement.setInt(1, identificadoDNI);
			deleteIdentificadoCentroStatement.executeUpdate();

			PreparedStatement deleteIdentificadoStatement = conn.prepareStatement(deleteIdentificadoQuery);
			deleteIdentificadoStatement.setInt(1, identificadoDNI);
			deleteIdentificadoStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void updateIdentificado(Identificado identificado, int identificadoDNI, int dniTestigo) {
		String updateIdentificadoQuery = "UPDATE `identificado` SET `nombreIdentificado` = ?, `DNIIdentificado` = ?, `lugarSecuestroIdentificado` = ?, `ultVezVistoIdentificado` = ?, `biografiaIdentificado` = ?, `materialIdentificado` = ?, `idTestigo` = ?, `idprofesion` = ? WHERE `DNIIdentificado` = ?";
		String updateIdentificadoCentroQuery = "DELETE FROM `ccdtye_identificado` WHERE `IdIdentificado` = ?";
		// TODO: BORRAR SEGUN ID IDENTIFICADO
		Testigo_DAO tdao = new Testigo_DAO();
		CCDTyE_DAO cdao = new CCDTyE_DAO();
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		int idTestigo = tdao.selectIdTestigo(dniTestigo);
		int idProfesion = selectIdProfesion(identificado.getProfesion());
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement updateIdentificadoCentroStatement = conn.prepareStatement(updateIdentificadoCentroQuery);
			updateIdentificadoCentroStatement.setInt(1, identificadoDNI);
			updateIdentificadoCentroStatement.executeUpdate();

			PreparedStatement updateIdentificadoStatement = conn.prepareStatement(updateIdentificadoQuery,
					Statement.RETURN_GENERATED_KEYS);
			updateIdentificadoStatement.setString(1, identificado.getNombreCompleto());
			updateIdentificadoStatement.setInt(2, identificado.getDNI());
			updateIdentificadoStatement.setString(3, identificado.getLugarSecuestro());
			updateIdentificadoStatement.setDate(4, Date.valueOf(identificado.getUltVezVisto()));
			updateIdentificadoStatement.setString(5, identificado.getBiografia());
			updateIdentificadoStatement.setString(6, identificado.getMaterial());
			updateIdentificadoStatement.setInt(7, idTestigo);
			updateIdentificadoStatement.setInt(8, idProfesion);
			updateIdentificadoStatement.setInt(9, identificadoDNI);
			updateIdentificadoStatement.executeUpdate();

			ResultSet rs = updateIdentificadoStatement.getGeneratedKeys();
			int idIdentificado = 0;
			if (rs.next()) {
				idIdentificado = rs.getInt("idIdentificado");

				ArrayList<CCDTyE> centros = identificado.getCentros();
				for (CCDTyE centro : centros) {
					insertCCDTyEAsociado(idIdentificado, cdao.selectIdCcdtye(centro.getNombre()));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public int selectIdIdentificado(int dni) {
		String query = "SELECT `idIdentificado` FROM `identificado` WHERE `DNIIdentificado` = ?";

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement identificadoStatement = conn.prepareStatement(query);
			identificadoStatement.setInt(1, dni);

			ResultSet rs = identificadoStatement.executeQuery();
			if (rs.next()) {
				return rs.getInt("idIdentificado");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return 0;
	}

	public ArrayList<String> selectAllProfesiones() {

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		String query = "SELECT nombreProfesion FROM `profesion`";
		ArrayList<String> profesiones = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				profesiones.add(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return profesiones;
	}

	public int selectIdProfesion(String nombre) {
		String query = "SELECT `idprofesion` FROM `profesion` WHERE `nombreProfesion` = ?";

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement idprofesion = conn.prepareStatement(query);
			idprofesion.setString(1, nombre);
			ResultSet rs = idprofesion.executeQuery();
			if (rs.next()) {
				return rs.getInt("idprofesion");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return 0;
	}

	public String selectProfesion(int idprofesion) {
		String query = "SELECT * FROM `profesion` WHERE `idprofesion` = ?";

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(query);
			prepareStatement.setInt(1, idprofesion);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {

				return rs.getString("nombreProfesion");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No pude");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return null;
	}
}