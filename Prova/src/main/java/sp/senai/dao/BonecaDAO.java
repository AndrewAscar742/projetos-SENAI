package sp.senai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sp.senai.model.Boneca;

public class BonecaDAO {
	private Connection conn;
	private FabricarConexao fabricaConexao = new FabricarConexao();

	public List<Boneca> listar() {
		List<Boneca> listaBoneca = new ArrayList<Boneca>();
		
		try {
			conn = fabricaConexao.fabricarConexaoMySQL();
			PreparedStatement pstm = conn.prepareStatement("SELECT * FROM boneca");
			pstm.execute();
			ResultSet rst = pstm.getResultSet();

			Boneca boneca;
			while (rst.next()) {
				int id = rst.getInt("idBoneca");
				String name = rst.getString("nome");
				String empresa = rst.getString("empresa");

				boneca = new Boneca(id, name, empresa);
				listaBoneca.add(boneca);
			}
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaBoneca;
	}

	public boolean alterar(Boneca boneca) {
		try {
			conn = fabricaConexao.fabricarConexaoMySQL();
			PreparedStatement pstm = conn.prepareStatement("UPDATE boneca SET ?, ? where idBoneca = ?");

			pstm.setString(1, boneca.getNome());
			pstm.setString(2, boneca.getEmpresa());
			pstm.setInt(3, boneca.getIdBoneca());

			pstm.execute();
			conn.close();

			if (Statement.RETURN_GENERATED_KEYS > 0)
				return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean excluir(Boneca boneca) {
		try {
			conn = fabricaConexao.fabricarConexaoMySQL();
			PreparedStatement pstm = conn.prepareStatement("DELETE FROM boneca WHERE idBoneca == ?");

			pstm.setInt(1, boneca.getIdBoneca());
			pstm.execute();
			
			conn.close();
			if (pstm.getUpdateCount() > 0) return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean adicionar(Boneca boneca) {
		try {
			conn = fabricaConexao.fabricarConexaoMySQL();
			PreparedStatement pstm = conn.prepareStatement("INSERT INTO boneca (nome, empresa) VALUES (?, ?)");
			
			pstm.setString(1, boneca.getNome());
			pstm.setString(2, boneca.getEmpresa());
			
			pstm.execute();
			conn.close();
			
			if (Statement.RETURN_GENERATED_KEYS > 0)
				return true;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	

}
