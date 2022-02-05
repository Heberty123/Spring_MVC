<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">


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
	
	<div class="container">
		<h1> Vaga id: <% out.println(id_String); %> excluído com sucesso</h1>
		<a class="btn btn-info" href="/BDVagas/listar.jsp">Voltar</a>
	</div>
	

<%
}
else {
%>

	Volte para lista para excluir algumas vagas ! <a href="/BDVagas/listar.jsp">voltar</a>

<%} %>


</body>
</html>