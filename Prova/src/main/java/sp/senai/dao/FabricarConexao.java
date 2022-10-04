package sp.senai.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricarConexao {
	
	public Connection fabricarConexaoMySQL() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql//localhost:3306/armazenamento_boneca?useTimezone=true&serverTimezone=UTC";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("", "root", "root");
		
		return conn;
	}
}
