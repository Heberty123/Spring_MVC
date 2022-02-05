<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<a href="./index.jsp">Home</a>
<a href="./gravar.jsp">Gravar</a>
<a href="./editar.jsp">Editar</a>
<a href="./excluir.jsp">Excluir</a>
<a href="./listar.jsp">Listar</a>

<%@ page import="classes.Vagas" %>
<%@ page import="classes.Banco" %>

<h1>Excluir vagas</h1>

<% 
Banco banco = new Banco();
String id_String = request.getParameter("id");
if (id_String != null){
	
	banco.deleteByID(Integer.parseInt(id_String));
%>
	
	<div>
		<h1> Vaga id: <% out.println(id_String); %> excluído com sucesso</h1>
	</div>

<%
}
else {
%>

	Volte para lista para excluir algumas vagas ! <a href="/BDVagas/listar.jsp">voltar</a>

<%} %>


</body>
</html>