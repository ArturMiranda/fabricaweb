package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

//http://localhost:8080/fabricaweb/usuario_controller
/*Fazendo o mapeamento do servlet*/
@WebServlet("/cadastro_externo_controller")
public class CadastroExternoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroExternoController() {
		System.out.println("CadastroExternoController Construtor...");
	}
	
	public void init() throws ServletException {
		System.out.println("CadastroExternoController Init...");
		super.init();
	}
	
	/**
	 * O método doPost no controlador {@link CadastroExternoController} é apenas para cadastros de usuarios sem logins
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("CadastroExternoController Posting...");
		
		String nomeUser = req.getParameter("nome");
		String logiUser = req.getParameter("login");
		String senhaUser = req.getParameter("senha");
		
		Usuario usuario = new Usuario();
		usuario.setNome(nomeUser);
		usuario.setLogin(logiUser);
		usuario.setSenha(senhaUser);
		
		resp.setContentType("text/html");  
		resp.setCharacterEncoding("UTF-8"); 
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.salvar(usuario) != 0){
			System.out.println("Cadastro realizado com sucesso!");
			resp.getWriter().write("<strong>Sucesso!</strong> cadastro realizado com sucesso!"); 
		} else {
			System.out.println("Não foi possível realizar a operação.");
			resp.getWriter().println("<b>Não foi possível realizar a operação.</b>");
		}
		
		//super.doPost(req, resp);
	}
	
	@Override
	public void destroy() {
		System.out.println("Destroy...");
		super.destroy();
	}
}
