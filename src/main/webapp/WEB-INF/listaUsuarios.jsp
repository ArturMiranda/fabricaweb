<%@ page import="java.util.List"%>
<%@ page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista de Usuários</title>
	<%@ include file="templateHead.html"%>
	<script>
	$(function(){
		$(".nav >li.active").removeClass("active");
		$("#userPage").addClass("active");
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
			     <h1>Usuários<small> gerenciamento</small></h1>
		    </div>
		</div>
		<div class="row justify-content-md-center">
		    <div class="col-md-8 col-md-offset-0">
		    
			    <a href="usuario_controller?acao=inserir">Inserir usuário</a>
			    <br> <br>
			    <div class="table-responsive">
				    <table class="table table-bordered table-striped table-hover" width="600" cellspacing="0" cellpadding="8" border="1">
				        <tr class="bg-primary"><th>Usuários</th><th>Login</th><th>Editar</th><th>Deletar</th></tr>
				        <%
				        List<Usuario> listaUsuarios = (List<Usuario>)request.getAttribute("listaUsuarios");
				        String url_editar = "-", url_deletar = "-", acao;
				        for (Usuario usuario : listaUsuarios) {
	                		url_editar = "usuario_controller?acao=buscar&id="+usuario.getId();
							url_deletar = "usuario_controller?acao=excluir&id="+usuario.getId();
	                    	acao = "<a class='btn btn-danger' href='"+url_deletar+"'>Deletar</a>";
	                		
	                		%>
	                		<tr>
	                			<td><%=usuario.getNome()%></td>
	                			<td><%=usuario.getLogin()%></td>
	                			<td><a class="btn btn-primary" href="<%=url_editar%>">Editar</a></td>
	                			<td><%=acao%></td>
	                		</tr>
	                		<%
				        }			
	                	%>
				    </table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>