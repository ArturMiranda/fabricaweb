package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.PreparedStatement;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection conexao = ConexaoFactory.getConnection();
	
	public int cadastrar(Usuario usuario) {
		String query = " INSERT INTO usuario (nome,login,senha) VALUES (?,?,?) ";
		int resposta = 0;
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			//Executando a query
			resposta = preparador.executeUpdate();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resposta;
	}

	public int alterar(Usuario usuario) {
		String query = " UPDATE usuario SET nome = ?, login = ?, senha = ? WHERE id = ? ";
		int resposta = 0;
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setString(1, usuario.getNome());
			preparador.setString(2, usuario.getLogin());
			preparador.setString(3, usuario.getSenha());
			preparador.setInt(4, usuario.getId());
			//Executando a query
			resposta = preparador.executeUpdate();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resposta;
	}

	public int excluir(Usuario usuario) {
		String query = " DELETE FROM usuario WHERE id = ? ";
		int resposta = 0;
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setInt(1, usuario.getId());
			//Executando a query
			resposta = preparador.executeUpdate();
			preparador.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resposta;
	}

	public int salvar(Usuario usuario) {
		int resposta = 0;
		if(usuario.getId() != null){
			resposta = alterar(usuario);/*Atualizacao do usuario*/
		} else {
			resposta = cadastrar(usuario);/*Cadastra novo usuario*/
		}
		
		return resposta;
	}

	/**
	 * Busca um registro no B.D. pelo id do usuário
	 * @param id é um inteiro que representa o usuário a ser buscado
	 * @return Um objeto usuário quando encontrado ou null
	 */
	public Usuario buscarPorId(Integer id) {
		String query = " SELECT * FROM usuario WHERE id = ? ";
		
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setInt(1, id);
			//Executando a query
			ResultSet resultado = preparador.executeQuery();/*Retorno do tipo ResultSet*/
			/*Posiciona o cursor no primeiro registro*/
			if(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Este metodo tem como objetivo buscar todos usuarios cadastrados.
	 * @return Retorna um lista de objetos usuarios vazia ou com N registros
	 */
	public List<Usuario> buscarTodos() {
		String query = " SELECT * FROM usuario ";
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Executando a query
			ResultSet resultado = preparador.executeQuery();/*Retorno do tipo ResultSet*/
			/*Posiciona o cursor no primeiro registro*/
			while(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				/*adicionado os usuarios a lista*/
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaUsuarios;
	}

	public Usuario autenticar(Usuario usuarioConsulta) {
		String query = " SELECT * FROM usuario WHERE login = ? AND senha = ? ";
		
		try {
			PreparedStatement preparador = (PreparedStatement) conexao.prepareStatement(query);
			//Substituindo as interrogacoes
			preparador.setString(1, usuarioConsulta.getLogin());
			preparador.setString(2, usuarioConsulta.getSenha());
			//Executando a query
			ResultSet resultado = preparador.executeQuery();/*Retorno do tipo ResultSet*/
			/*Posiciona o cursor no primeiro registro*/
			if(resultado.next()){
				Usuario usuarioEncontrado = new Usuario();
				usuarioEncontrado.setId(resultado.getInt("id"));
				usuarioEncontrado.setNome(resultado.getString("nome"));
				usuarioEncontrado.setLogin(resultado.getString("login"));
				
				return usuarioEncontrado;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
