package olimpo_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import olimpo.CCDTyE;
import olimpo.NoIdentificado;
import olimpo.Testigo;

public class NoIdentificado_DAO {

	private String url = "jdbc:mysql://localhost:3306/siksi";
	private String usuario = "root";
	private String contrasenia = "admin";
	private Testigo_DAO tdao = new Testigo_DAO();
	private CCDTyE_DAO cdao = new CCDTyE_DAO();
	private Connection conn = null;

	public void insertNoIdentificado(NoIdentificado noIdentificado, int dniT) {
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement preparedStatement = conn.prepareStatement(
					"INSERT INTO `no_identificado` (`apodoNo_identificado`, `descripcionesNo_identificado`, `idTestigo`) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, noIdentificado.getApodo());
			preparedStatement.setString(2, noIdentificado.getDescripciones());
			int dnitestigo = selectIdTestigo(dniT);

			preparedStatement.setInt(3, dnitestigo);

			int i = preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {

				ArrayList<CCDTyE> centros = noIdentificado.getCentros();
				for (CCDTyE centro : centros) {
					insertCCDTyEAsociado(selectIdNoIdentificado(noIdentificado.getApodo()),
							cdao.selectIdCcdtye(centro.getNombre()));
				}
			}
			System.out.println(i);

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

	public ArrayList<NoIdentificado> selectAllNoIdentificado() {
		String query = "SELECT * FROM `no_identificado`";
		ArrayList<NoIdentificado> noIdentificados = new ArrayList<>();

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			NoIdentificado noidentificado = new NoIdentificado("", "", null, null);
			while (rs.next()) {
				int idTestigo = rs.getInt("idTestigo");
				Testigo testigo = tdao.selectTestigo(idTestigo);

				ArrayList<CCDTyE> centros = cdao
						.selectCCDTyEAsociadosANoIdentificado(rs.getString("apodoNo_identificado"));
				noidentificado.setApodo(rs.getString("apodoNo_identificado"));
				noidentificado.setDescripciones(rs.getString("descripcionesNo_identificado"));
				noidentificado.setTestigoAsociado(testigo);
				;
				noidentificado.setCentros(centros);
				noIdentificados.add(noidentificado);
			}

		} catch (SQLException e) {
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

		return noIdentificados;
	}

	public int selectIdTestigo(int dni) {
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement idTestigo = conn
					.prepareStatement("SELECT `idtestigo` FROM `testigo` WHERE `DNITestigo` = ?");
			idTestigo.setInt(1, dni);
			ResultSet rs = idTestigo.executeQuery();
			while (rs.next()) {
				return rs.getInt("idtestigo");
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

	private void insertCCDTyEAsociado(int idNoIdentificado, int idCcdtye) {

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(
					"INSERT INTO `ccdtye_no_identificado` (`id_no_identificado`, `idccdtye1`) VALUES (?, ?)");
			prepareStatement.setInt(1, idNoIdentificado);
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

	public int selectIdNoIdentificado(String apodo) {
		String query = "SELECT `idno_identificado` FROM `no_identificado` WHERE `apodoNo_identificado` = ?";
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement identificadoStatement = conn.prepareStatement(query);
			identificadoStatement.setString(1, apodo);
			ResultSet rs = identificadoStatement.executeQuery();
			while (rs.next()) {
				return rs.getInt("idno_identificado");
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

	public void deleteNoIdentificado(String apodo) {

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		String deleteNoIdentificadoQuery = "DELETE FROM `no_identificado` WHERE `apodoNo_identificado` = ?";
		String deleteNoIdentificadoCentroQuery = "DELETE FROM `ccdtye_no_identificado` WHERE `id_no_identificado` = ?";

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement deleteIdentificadoCentroStatement = conn
					.prepareStatement(deleteNoIdentificadoCentroQuery);
			deleteIdentificadoCentroStatement.setInt(1, selectIdNoIdentificado(apodo));
			deleteIdentificadoCentroStatement.executeUpdate();

			PreparedStatement deleteIdentificadoStatement = conn.prepareStatement(deleteNoIdentificadoQuery);
			deleteIdentificadoStatement.setString(1, apodo);
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

	public void updateNoIdentificado(NoIdentificado noidentificado, String apodo, int dniTestigo) {
		String updateIdentificadoQuery = "UPDATE `no_identificado` SET `apodoNo_identificado`=?, `descripcionesNo_identificado`=?, `idTestigo`=? WHERE `apodoNo_identificado` = ?";
		String updateNoIdentificadoCentroQuery = "DELETE FROM `ccdtye_no_identificado` WHERE `id_no_identificado` = ?";
		// TODO: BORRAR SEGUN ID IDENTIFICADO
		Testigo_DAO tdao = new Testigo_DAO();
		CCDTyE_DAO cdao = new CCDTyE_DAO();
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		int idTestigo = tdao.selectIdTestigo(dniTestigo);

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			PreparedStatement updateNoIdentificadoCentroStatement = conn
					.prepareStatement(updateNoIdentificadoCentroQuery);
			updateNoIdentificadoCentroStatement.setInt(1, selectIdNoIdentificado(apodo));
			updateNoIdentificadoCentroStatement.executeUpdate();

			PreparedStatement updateIdentificadoStatement = conn.prepareStatement(updateIdentificadoQuery,
					Statement.RETURN_GENERATED_KEYS);
			updateIdentificadoStatement.setString(1, noidentificado.getApodo());
			updateIdentificadoStatement.setString(2, noidentificado.getDescripciones());
			updateIdentificadoStatement.setInt(3, idTestigo);
			updateIdentificadoStatement.setString(4, apodo);

			updateIdentificadoStatement.executeUpdate();

			ResultSet rs = updateIdentificadoStatement.getGeneratedKeys();
			int idnoIdentificado = 0;
			if (rs.next()) {
				idnoIdentificado = rs.getInt("idno_identificado");

				ArrayList<CCDTyE> centros = noidentificado.getCentros();
				for (CCDTyE centro : centros) {
					insertCCDTyEAsociado(idnoIdentificado, cdao.selectIdCcdtye(centro.getNombre()));
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

}
