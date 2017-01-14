package br.com.fabricadeprogramador;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testeCadastrar();
		//testeAlterar();
		testeExcluir();
	}
	
	/*INSERCAO*/
	public static void testeCadastrar(){
		//Criando uma instancia de usuario
		Usuario usuario = new Usuario();
		usuario.setNome("Ana");
		usuario.setLogin("ana@gmail");
		usuario.setSenha("123");
		
		//Criando a class de cadastro do usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.cadastrar(usuario);
		
		System.out.println("Cadastrado com sucesso!");
	}
	
	/*ALTERACAO*/
	public static void testeAlterar(){
		//Criando uma instancia de usuario
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome("Ana paula");
		usuario.setLogin("ana@gmail");
		usuario.setSenha("123456");
		
		//Criando a class de cadastro do usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.alterar(usuario);
		
		System.out.println("Alterado com sucesso!");
	}

	/*EXCLUSAO*/
	public static void testeExcluir(){
		//Criando uma instancia de usuario
		Usuario usuario = new Usuario();
		usuario.setId(1);
		
		//Criando a class de cadastro do usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
		
		System.out.println("Excluído com sucesso!");
	}

}
