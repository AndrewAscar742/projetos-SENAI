package sp.senai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricarConexao {
	
	public Connection fabricarConexaoMySQL() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3307/arm_boneca?useTimezone=true&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(url, "root", "");
		
		return conn;
	}
}
