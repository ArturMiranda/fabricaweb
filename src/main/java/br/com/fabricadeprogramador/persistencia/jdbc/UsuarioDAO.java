package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.PreparedStatement;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection conexao = ConexaoFactory.getConnection();
	
	public void cadastrar(Usuario usuario) {
		String query = " INSERT INTO usuario (nome,login,senha) VALUES (?,?,?) ";
		
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			//Executando a query
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alterar(Usuario usuario) {
		String query = " UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE id = ? ";
		
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			//Executando a query
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usuario) {
		String query = " DELETE FROM usuario WHERE id = ? ";
		
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setInt(1, usuario.getId());
			//Executando a query
			preparador.execute();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
