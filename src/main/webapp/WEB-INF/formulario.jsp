<%@page import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Edição</title>
	<%@ include file="templateHead.html"%>
	<script>
	$(function(){
		$(".nav >li.active").removeClass("active");
		$("#userPage").addClass("active");
	});
	</script>
</head>
<body>
<div class="container">
		<div class="row justify-content-md-center">
			<div class="col-md-8 col-md-offset-0">
				<%@ include file="templateMenu.html"%>
			</div>
		</div>
		<div class="row justify-content-md-center">
			<div class="col-md-8 col-md-offset-0">			
			     <h1>Usuário<small> edição</small></h1>
		    </div>
		</div>
		<div class="row justify-content-md-center">
		    <div class="col-md-8 col-md-offset-0">
			<%
			String nome ="",login="", senha="", id="";
			Usuario usuario = (Usuario) request.getAttribute("usuario");
			if(usuario != null){
				nome = usuario.getNome();
				login = usuario.getLogin();
				senha = usuario.getSenha();
				id = Integer.toString(usuario.getId());/*Transformando o Id em String*/
			} 
			%>
			<form action="usuario_controller" method="post">
				<br>Nome 	<input type="text" name="nome" value="<%=nome %>" placeholder="nome" style="margin-top:10px">
				<br>Login	<input type="text" name="login" value="<%=login %>" placeholder="login" style="margin-top:10px">
				<br>Senha	<input type="text" name="senha" value="<%=senha %>" placeholder="senha" style="margin-top:10px">
				<input type="hidden" name="id" value="<%=id %>" placeholder="id" style="margin-top:10px">
				
				<br><input class="btn btn-primary" type="submit" name="Enviar" value="Enviar" style="margin-top:10px">
			</form>
			</div>
		</div>
	</div>
</body>
</html>