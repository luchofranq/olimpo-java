package olimpo_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import olimpo.Testigo;

public class Testigo_DAO {

	public void insertTestigo(Testigo testigo) {
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(
					"INSERT INTO `testigo` (`nombreCompletoTestigo`, `DNItestigo`, `testimonioTestigo`) VALUES (?, ?, ?)");
			prepareStatement.setString(1, testigo.getNombreCompleto());
			prepareStatement.setInt(2, testigo.getDNI());
			prepareStatement.setString(3, testigo.getTestimonio());
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

	public ArrayList<Testigo> selectAllTestigos() {
		String query = "SELECT * FROM `testigo`";
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;
		ArrayList<Testigo> testigos = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Testigo testigo = new Testigo(rs.getString("nombreCompletoTestigo"), rs.getInt("DNItestigo"),
						rs.getString("testimonioTestigo"));
				testigos.add(testigo);
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

		return testigos;
	}

	public void deleteTestigo(Testigo testigo) {
		String query = "DELETE FROM `testigo` WHERE `DNItestigo` = ?";
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(query);
			prepareStatement.setInt(1, testigo.getDNI());
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

	public void updateTestigo(Testigo testigo, int dni) {
		String query = "UPDATE `testigo` SET `nombreCompletoTestigo` = ?, `DNItestigo` = ?, `testimonioTestigo` = ? WHERE `DNItestigo` = ?";
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(query);

			prepareStatement.setString(1, testigo.getNombreCompleto());
			prepareStatement.setInt(2, testigo.getDNI());
			prepareStatement.setString(3, testigo.getTestimonio());
			prepareStatement.setInt(4, dni);

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

	public Testigo selectTestigo(int idtestigo) {
		String query = "SELECT * FROM `testigo` WHERE `idtestigo` = ?";
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement prepareStatement = conn.prepareStatement(query);
			prepareStatement.setInt(1, idtestigo);
			ResultSet rs = prepareStatement.executeQuery();
			if (rs.next()) {

				String nombreCompleto = rs.getString("nombreCompletoTestigo");
				int dni = rs.getInt("DNItestigo");
				String testimonio = rs.getString("testimonioTestigo");
				return new Testigo(nombreCompleto, dni, testimonio);
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

	public int selectIdTestigo(int dni) {
		String query = "SELECT `idtestigo` FROM `testigo` WHERE `DNITestigo` = ?";
		String url = "jdbc:mysql://localhost:3306/siksi";
		String usuario = "root";
		String contrasenia = "admin";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, usuario, contrasenia);
			PreparedStatement idTestigo = conn.prepareStatement(query);
			idTestigo.setInt(1, dni);
			ResultSet rs = idTestigo.executeQuery();
			if (rs.next()) {
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

}
