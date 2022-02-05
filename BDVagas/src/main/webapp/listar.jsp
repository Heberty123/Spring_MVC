<%@page import="java.util.ArrayList"%>
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

String des = request.getParameter("des");
String reqO = request.getParameter("reqO");
String reqD = request.getParameter("reqD");
String rem = request.getParameter("rem");
String ben = request.getParameter("ben");
String local = request.getParameter("local");
String aberta = request.getParameter("aberta");

if(des != null){

	banco.create(des, reqO, reqD, rem, ben, local, Integer.parseInt(aberta));
}


int number = 0;
String abertura = request.getParameter("abertura");
if(abertura != null){
	number = Integer.parseInt(abertura);
}

%>
<h1>Lista de Vagas</h1>
<h2>Deseja que vagas ? <a href="/BDVagas/listar.jsp?abertura=1">Abertas</a> ou <a href="/BDVagas/listar.jsp?abertura=0">Encerradas</a></h2>

<% for(Vagas var: banco.listar(number)){ %>

	<div>
		<h1>Id: <% out.println(var.getId()); %><a href="./excluir.jsp?id=<%out.println(var.getId());%>">Apagar </a><a href="./editar.jsp?id=<%out.println(var.getId());%>"> Editar</a></h1>
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