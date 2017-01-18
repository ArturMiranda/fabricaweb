package br.com.fabricadeprogramador.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*Ativando o filte para qualquer tipo de request*/
@WebFilter(dispatcherTypes={DispatcherType.REQUEST}, urlPatterns="/*")
public class FiltroAutenticacao implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String uri = httpRequest.getRequestURI();
		
		HttpSession sessao = httpRequest.getSession(false);
		if( sessao != null || uri.lastIndexOf(".html") != -1 || uri.lastIndexOf("login_controller") != -1 || uri.lastIndexOf("cadastro_externo_controller") != -1){
			System.out.println("Passou no filtro!");
			chain.doFilter(request, response);
		} else {
			System.out.println("Reprovado no filtro!");
			httpResponse.sendRedirect("login.html");
			//request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
