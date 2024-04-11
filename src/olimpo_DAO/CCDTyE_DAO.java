package olimpo_DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import olimpo.CCDTyE;

public class CCDTyE_DAO {

	public void insertCCDTyE(CCDTyE CCDTyE) {
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(
					"INSERT INTO `ccdtye` (`nombreCCDTyE`, `ubicacionCCDTyE`, `fechaInicioCCDTyE`, `fechaCierreCCDTyE`, `policia`, `gendarmeria`, `ejercito`  ) VALUES (?,?, ?,?,?,?,?)");
			prepareStatement.setString(1, CCDTyE.getNombre());
			prepareStatement.setString(2, CCDTyE.getUbicacion());
			Date f = Date.valueOf(CCDTyE.getFechaInicio());
			prepareStatement.setDate(3, f);
			Date fe = Date.valueOf(CCDTyE.getFechaCierre());
			prepareStatement.setDate(4, fe);
			boolean[] responsable = CCDTyE.getResponsable();
			boolean policia = responsable[olimpo.CCDTyE.POLICIA];
			boolean gendarmeria = responsable[olimpo.CCDTyE.GENDARMERIA];
			boolean ejercito = responsable[olimpo.CCDTyE.EJERCITO];
			prepareStatement.setBoolean(5, policia);
			prepareStatement.setBoolean(6, gendarmeria);
			prepareStatement.setBoolean(7, ejercito);
			int i = prepareStatement.executeUpdate();
			System.out.println(i);

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

	public ArrayList<CCDTyE> selectAllCCDTyEs() {

		String query = "select * from `ccdtye`";
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		ArrayList<CCDTyE> CCDTyEs = new ArrayList<>();

		try {
			// get connection
			conn = DriverManager.getConnection(url, usuario, contrasenia);

			// create statement
			Statement statement = conn.createStatement();

			// execute query
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {

				boolean[] aux = { rs.getBoolean("policia"), rs.getBoolean("gendarmeria"), rs.getBoolean("ejercito") };

				CCDTyE CCDTyE = new CCDTyE(rs.getString("nombreCCDTyE"), rs.getString("ubicacionCCDTyE"),
						rs.getDate("fechaInicioCCDTyE").toLocalDate(), rs.getDate("fechaCierreCCDTyE").toLocalDate(),
						aux);
				CCDTyEs.add(CCDTyE);

			}

		} catch (Exception e) {
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
		return CCDTyEs;

	}

	public void deleteCCDTyE(CCDTyE c) {
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		String query = "delete from `ccdtye` where `nombreCCDTyE`= ?";
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(query);
			prepareStatement.setString(1, c.getNombre());
			int i = prepareStatement.executeUpdate();
			System.out.println(i);

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

	public void updateCCDTyE(CCDTyE CCDTyE, String nombre) {
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		String query = "update `ccdtye` set `nombreCCDTyE` = ?, `ubicacionCCDTyE` = ?,`fechaInicioCCDTyE` = ?, `fechaCierreCCDTyE` = ?, `policia` = ?,`gendarmeria` = ?,`ejercito` = ? where `nombreCCDTyE`= ?";

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(query);

			prepareStatement.setString(1, CCDTyE.getNombre());
			prepareStatement.setString(2, CCDTyE.getUbicacion());
			Date f = Date.valueOf(CCDTyE.getFechaInicio());
			prepareStatement.setDate(3, f);
			Date fe = Date.valueOf(CCDTyE.getFechaCierre());
			prepareStatement.setDate(4, fe);
			boolean[] responsable = CCDTyE.getResponsable();
			boolean policia = responsable[olimpo.CCDTyE.POLICIA];
			boolean gendarmeria = responsable[olimpo.CCDTyE.GENDARMERIA];
			boolean ejercito = responsable[olimpo.CCDTyE.EJERCITO];
			prepareStatement.setBoolean(5, policia);
			prepareStatement.setBoolean(6, gendarmeria);
			prepareStatement.setBoolean(7, ejercito);
			prepareStatement.setString(8, nombre);

			int i = prepareStatement.executeUpdate();
			System.out.println(i);

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

	public ArrayList<CCDTyE> selectCCDTyEAsociadosAIdentificado(int idIdentificado) {
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		ArrayList<CCDTyE> CCDTyEs = new ArrayList<>();
		ArrayList<Integer> centroids = new ArrayList<>();
		String identificadoQuery = "SELECT * FROM `ccdtye_identificado` WHERE `idIdentificado` = ?";
		String ccdtyeQuery = "SELECT * FROM `ccdtye` WHERE `idCCDTyE` IN (SELECT `idCcdtye` FROM `ccdtye_identificado` WHERE `idIdentificado` = ?)";

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			// RECIBE LOS CENTROS CON DETERMINADO IDIDENTIFICADO EN LA TABLA CONJUNTA
			PreparedStatement identificadoStatement = conn.prepareStatement(identificadoQuery);
			identificadoStatement.setInt(1, idIdentificado);
			ResultSet identificadoResultSet = identificadoStatement.executeQuery();
			// GUARDA LOS ID DE LOS CENTROS CON UN MISMO IDENTIFICADO
			while (identificadoResultSet.next()) {
				centroids.add(identificadoResultSet.getInt("idCcdtye"));
			}
			// DE CADA CENTRO RECIBE LOS DATOS
			PreparedStatement ccdtyeStatement = conn.prepareStatement(ccdtyeQuery);
			for (int id : centroids) {
				ccdtyeStatement.setInt(1, id);
				ResultSet ccdtyeResultSet = ccdtyeStatement.executeQuery();

				while (ccdtyeResultSet.next()) {
					String nombreCCDTyE = ccdtyeResultSet.getString("nombreCCDTyE");
					String ubicacionCCDTyE = ccdtyeResultSet.getString("ubicacionCCDTyE");
					LocalDate fechaInicioCCDTyE = ccdtyeResultSet.getDate("fechaInicioCCDTyE").toLocalDate();
					LocalDate fechaCierreCCDTyE = ccdtyeResultSet.getDate("fechaCierreCCDTyE").toLocalDate();
					boolean[] responsable = { ccdtyeResultSet.getBoolean("policia"),
							ccdtyeResultSet.getBoolean("gendarmeria"), ccdtyeResultSet.getBoolean("ejercito") };

					CCDTyE centro = new CCDTyE(nombreCCDTyE, ubicacionCCDTyE, fechaInicioCCDTyE, fechaCierreCCDTyE,
							responsable);
					CCDTyEs.add(centro);
				}
			}

		} catch (SQLException e) {
			System.out.println("error");
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

		return CCDTyEs;
	}

	public int selectIdCcdtye(String nombre) {
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement idcentro = conn
					.prepareStatement("SELECT `idCCDTyE` FROM `ccdtye` WHERE `nombreCCDTyE` = ?");
			idcentro.setString(1, nombre);

			ResultSet rs = idcentro.executeQuery();
			if (rs.next()) {
				return rs.getInt("idCCDTyE");
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

	public ArrayList<CCDTyE> selectCCDTyEAsociadosANoIdentificado(String apodo) {
		NoIdentificado_DAO nidao = new NoIdentificado_DAO();
		String identificadoQuery = "SELECT * FROM `ccdtye_no_identificado` WHERE `id_no_identificado` = ?";
		String ccdtyeQuery = "SELECT * FROM `ccdtye` WHERE `idCCDTyE` IN (SELECT `idccdtye1` FROM `ccdtye_no_identificado` WHERE `id_no_identificado` = ?)";

		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		ArrayList<CCDTyE> CCDTyEs = new ArrayList<>();
		ArrayList<Integer> centroids = new ArrayList<>();
		int idNIdentificado = nidao.selectIdNoIdentificado(apodo);
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			// RECIBE LOS CENTROS CON DETERMINADO IDIDENTIFICADO EN LA TABLA CONJUNTA
			PreparedStatement identificadoStatement = conn.prepareStatement(identificadoQuery);
			identificadoStatement.setInt(1, idNIdentificado);
			ResultSet identificadoResultSet = identificadoStatement.executeQuery();

			// GUARDA LOS ID DE LOS CENTROS CON UN MISMO IDENTIFICADO
			while (identificadoResultSet.next()) {
				centroids.add(identificadoResultSet.getInt("idccdtye1"));
			}
			// DE CADA CENTRO RECIBE LOS DATOS
			PreparedStatement ccdtyeStatement = conn.prepareStatement(ccdtyeQuery);
			for (int id : centroids) {
				ccdtyeStatement.setInt(1, id);
				ResultSet ccdtyeResultSet = ccdtyeStatement.executeQuery();

				while (ccdtyeResultSet.next()) {
					String nombreCCDTyE = ccdtyeResultSet.getString("nombreCCDTyE");
					String ubicacionCCDTyE = ccdtyeResultSet.getString("ubicacionCCDTyE");
					LocalDate fechaInicioCCDTyE = ccdtyeResultSet.getDate("fechaInicioCCDTyE").toLocalDate();
					LocalDate fechaCierreCCDTyE = ccdtyeResultSet.getDate("fechaCierreCCDTyE").toLocalDate();
					boolean[] responsable = { ccdtyeResultSet.getBoolean("policia"),
							ccdtyeResultSet.getBoolean("gendarmeria"), ccdtyeResultSet.getBoolean("ejercito") };

					CCDTyE centro = new CCDTyE(nombreCCDTyE, ubicacionCCDTyE, fechaInicioCCDTyE, fechaCierreCCDTyE,
							responsable);
					CCDTyEs.add(centro);
				}
			}

		} catch (SQLException e) {
			System.out.println("error");
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

		return CCDTyEs;
	}
}
