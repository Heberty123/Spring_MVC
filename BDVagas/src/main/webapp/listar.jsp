<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./resource/css/estilo.css">
</head>
<body>

<a href="./index.jsp">Home</a>
<a href="./gravar.jsp">Gravar</a>
<a href="./editar.jsp">Editar</a>
<a href="./excluir.jsp">Excluir</a>
<a href="./listar.jsp">Listar</a>

<%@ page import="classes.Vagas" %>
<%@ page import="classes.Banco" %>

<% 
Banco banco = new Banco();
for(Vagas var: banco.listar(0)){
%>
	<div>
		<h1>Id: <% out.println(var.getId()); %></h1>
		<p>Descrição: <% out.println(var.getDescricao()); %></p>
		<h2>Re.Obrigatórios: <% out.println(var.getRequisitosObrigatorios()); %></h2>
		<h2>Re.Desejaveis: <% out.println(var.getRequisitosDesejaveis()); %></h2>
		<h2>Remuneração: <% out.println(var.getRemuneracao()); %></h2>
		<h2>Benefícios: <% out.println(var.getBeneficio()); %></h2>
		<h2>Local de Trabalho: <% out.println(var.getLocalDeTrabalho()); %></h2>
		<h2>Aberta: <% out.println(var.getAberta()); %></h2>
	</div>
<%
}
%>

</body>
</html>