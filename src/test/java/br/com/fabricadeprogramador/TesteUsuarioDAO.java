package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testeCadastrar(10);// quatidade de registros
		//testeAlterar();
		//testeExcluir();
		//testeSalvar();
		//testeBuscar();
		//testeBuscarTodos();
		testeAutenticar();
	}
	
	/*INSERCAO*/
	public static void testeCadastrar(Integer qtdRegistros){
		GeradorAleatorio gerador = new GeradorAleatorio();
		String nomeUser;
		String loginUser;
		String senha;
		
		for(Integer i=0;i<qtdRegistros;i++){
			
			//Gerando os dados aleatorios
			nomeUser = gerador.criarNomeAleatorio();
			String[] split = nomeUser.split(" ");
			loginUser = split[0] +"_"+split[(split.length-1)] + "@gmail.com";
			senha = gerador.criarNumeroAleatorio(5);
			
			//Criando uma instancia de usuario
			Usuario usuario = new Usuario();
			usuario.setNome(nomeUser);
			usuario.setLogin(loginUser.toLowerCase());
			usuario.setSenha(senha);
			
			//Invocando a class de cadastro do usuario
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.cadastrar(usuario);
			
			System.out.println("Cadastrado com sucesso!");
		}
	}
	
	/*ALTERACAO*/
	public static void testeAlterar(){
		//Criando uma instancia de usuario
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome("Ana paula");
		usuario.setLogin("ana@gmail");
		usuario.setSenha("123456");
		
		//Invocando a class de cadastro do usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.alterar(usuario);
		
		System.out.println("Alterado com sucesso!");
	}

	/*EXCLUSAO*/
	public static void testeExcluir(){
		//Criando uma instancia de usuario
		Usuario usuario = new Usuario();
		usuario.setId(1);
		
		//Invocando a class de cadastro do usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usuario);
		
		System.out.println("Excluído com sucesso!");
	}
	
	/*SALVAR*/
	public static void testeSalvar(){
		
		System.out.println("Teste 1 (atualizaçao):");
		//Criando uma instancia de usuario
		Usuario usuario = new Usuario();
		usuario.setId(1);
		usuario.setNome("André De Jesus Novais");
		usuario.setLogin("andre_novais@gmail.com");
		usuario.setSenha("51026");
		
		//Invocando a class de cadastro do usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		System.out.println("Dados atualizado com sucesso!\n");
		
		System.out.println("Teste 2 (inserção):");
		usuario = new Usuario();
		usuario.setNome("Novo usuario");
		usuario.setLogin("novo_usuario@gmail.com");
		usuario.setSenha("123456");

		//Invocando a class de cadastro do usuario
		usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
		
		System.out.println("Usuário inserido com sucesso!\n");
	}
	
	/*BUSCAR UM USUARIO*/
	public static void testeBuscar(){
		//Invocando a class DAO usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscarPorId(10);
		
		if(usuario != null)
			System.out.println("Usuário encontrado: \n"+usuario);
		else 
			System.out.println("Usuário não encontrado.");
	}
	
	/*BUSCAR TODOS*/
	public static void testeBuscarTodos(){
		//Invocando a class DAO usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> listaUsuarios = usuarioDAO.buscarTodos();
		
		if(listaUsuarios.size() > 0) {
			System.out.println("Usuários: \n");
			/*Imprimindo os usuarios*/
			for(Usuario user: listaUsuarios) {
				System.out.println(user);
			}
		}
		else { 
			System.out.println("Nenhum registro foi encontrado.\n");
		}
	}
	
	
	public static void testeAutenticar(){

		System.out.println("Teste 1 (true):");
		//Criando uma instancia de usuario
		Usuario usuario = new Usuario();
		usuario.setLogin("andre_novais@gmail.com");
		usuario.setSenha("51026");
		
		//Invocando a class de cadastro do usuario
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarioEncontrado = usuarioDAO.autenticar(usuario);
		if(usuarioEncontrado != null){
			System.out.println("Seja bem vindo, "+usuarioEncontrado.getNome()+"!\n");
		} else {
			System.out.println("Dados inválidos!\n");
		}
		
		System.out.println("Teste 2 (false):");
		usuario = new Usuario();
		usuario.setLogin("novo_usuario@gmail.com");
		usuario.setSenha("777777");

		//Invocando a class de cadastro do usuario
		usuarioDAO = new UsuarioDAO();
		usuarioEncontrado = usuarioDAO.autenticar(usuario);
		if(usuarioEncontrado != null){
			System.out.println("Seja bem vindo, "+usuarioEncontrado.getNome()+"!\n");
		} else {
			System.out.println("Dados inválidos!\n");
		}
	}
}
