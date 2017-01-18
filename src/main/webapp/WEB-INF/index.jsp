<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Home</title>
	<%@ include file="templateHead.html"%>
	
	<script>
	$(function(){
		$(".nav >li.active").removeClass("active");
		$("#homePage").addClass("active");
	});
	</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row justify-content-md-center">
			<div class="col-md-8 col-md-offset-0">
				<%@ include file="templateMenu.html"%>
			</div>
		</div>
		<div class="row justify-content-md-center">
			<div class="col-md-8 col-md-offset-0">
				<div class="page-header">			
				    <h2>Bem vindo! 
						<small><%
						Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioAutenticado");
						
						out.print(usuario.getNome());
						%></small>
					</h2>
			    </div>			
		    </div>
		</div>
	</div>
</body>
</html>