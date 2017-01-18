package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

/*Fazendo o mapeamento do servlet*/
@WebServlet("/login_controller")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		Usuario usuarioConsulta = new Usuario();
		usuarioConsulta.setLogin(login);
		usuarioConsulta.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuarioAutenticado = usuarioDAO.autenticar(usuarioConsulta);
		
		if(usuarioAutenticado != null){/*Logado*/
			/*Criando SESSION*/
			HttpSession sessao = req.getSession();
			sessao.setAttribute("usuarioAutenticado", usuarioAutenticado);
			sessao.setMaxInactiveInterval(60*5);/*determinado em segundos*/
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
			
		} else {/*Autenticaçao fail*/
			resp.getWriter().println("<script>alert('Usuário não encontrado!'); location.href = 'login.html'; </script>");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		
		if(acao != null && acao.equals("logout")){/*Excluir um registro do banco*/
			HttpSession sessao = req.getSession(false);
			if(sessao != null){
				sessao.invalidate();
				resp.sendRedirect("login.html");
			}
		} else {
			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		}
	}
}
