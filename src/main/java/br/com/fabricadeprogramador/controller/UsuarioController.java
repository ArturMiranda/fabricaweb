package br.com.fabricadeprogramador.controller;

import java.util.List;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

//http://localhost:8080/fabricaweb/usuario_controller
/*Fazendo o mapeamento do servlet*/
@WebServlet("/usuario_controller")
public class UsuarioController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioController() {
		System.out.println("UsuarioController Construtor...");
	}
	
	public void init() throws ServletException {
		System.out.println("UsuarioController Init...");
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Getting...");
		
		resp.setContentType("text/html");
		String acao = req.getParameter("acao");
		
		if(acao != null && acao.equals("excluir")){/*Excluir um registro do banco*/
			String idUser = req.getParameter("id");
			
			if(idUser != ""){
				Usuario usuario = new Usuario();
				usuario.setId(Integer.parseInt(idUser));
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				if(usuarioDAO.excluir(usuario) != 0){
					System.out.println("Excluído com sucesso!");
					/*redirecionando para visualizacao*/
					resp.sendRedirect("usuario_controller?acao=visualizar");
				} else {
					System.out.println("Não foi possível realizar a operação.");
					resp.getWriter().println("<b>Não foi possível realizar a operação.</b>");
				}
			} else {
				System.out.println("Não foi possível realizar a operação.");
				resp.getWriter().println("<b>Não foi possível realizar a operação.</b>");
			}
		} else if(acao != null && acao.equals("visualizar")){/*Listar todos os registros do banco*/
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> listaUsuarios = usuarioDAO.buscarTodos();
			
			req.setAttribute("listaUsuarios", listaUsuarios);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listaUsuarios.jsp");
			dispatcher.forward(req, resp);
		} else if(acao != null && acao.equals("buscar")){/*Buscar um registro do banco*/
			String idUser = req.getParameter("id");
			
			if(idUser != ""){
				int id = Integer.parseInt(idUser);
				Usuario usuario = new Usuario();
				usuario.setId(id);
				
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				Usuario respostaBusca = usuarioDAO.buscarPorId(id);
				if(respostaBusca != null){
					req.setAttribute("usuario", respostaBusca);
					RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formulario.jsp");
					dispatcher.forward(req, resp);
					System.out.println("Editando usuário.");
					//resp.getWriter().println("<b>Editando usuário.</b>");
				} else {
					System.out.println("Não foi possível realizar a operação.");
					resp.getWriter().println("<b>Não foi possível realizar a operação.</b>");
				}
			} else {
				System.out.println("Não foi possível realizar a operação.");
				resp.getWriter().println("<b>Não foi possível realizar a operação.</b>");
			}
		} else if(acao != null && acao.equals("inserir")){/*Inserir um registro do banco*/
			
			System.out.println("Inserção de usuário.");
		
			req.setAttribute("usuario", null);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/formulario.jsp");
			dispatcher.forward(req, resp);
				
			
			/*redirecionando para insercao*/
			//resp.sendRedirect("formulario.jsp");
				
		}
		//super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Posting...");
		
		String nomeUser = req.getParameter("nome");
		String logiUser = req.getParameter("login");
		String senhaUser = req.getParameter("senha");
		String idUser = "";
		
		Usuario usuario = new Usuario();
		usuario.setNome(nomeUser);
		usuario.setLogin(logiUser);
		usuario.setSenha(senhaUser);
		
		if(req.getParameter("id") != null && req.getParameter("id") != ""){
			idUser = req.getParameter("id");
			usuario.setId(Integer.parseInt(idUser));			
		}
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.salvar(usuario) != 0){
			System.out.println("Salvo com sucesso!");
			resp.sendRedirect("usuario_controller?acao=visualizar");
			//resp.getWriter().println("<b>Salvo com sucesso!</b>");
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
