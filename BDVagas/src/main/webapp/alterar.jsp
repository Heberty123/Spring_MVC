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


<%@ page import="classes.Vagas" %>
<%@ page import="classes.Banco" %>

<% 

String id = request.getParameter("id");
String des = request.getParameter("des");
String reqO = request.getParameter("reqO");
String reqD = request.getParameter("reqD");
String rem = request.getParameter("rem");
String ben = request.getParameter("ben");
String local = request.getParameter("local");
String aberta = request.getParameter("aberta");

Banco banco = new Banco();
banco.Editar(Integer.parseInt(id), des, reqO, reqD, rem, ben, local, Integer.parseInt(aberta));



%>

<h2> Vaga id <%out.print(id); %> foi alterada com sucesso !</h2>

	<div class="container mt-4">
		<div class="row">
			<h2>Descrição: <span><%out.print(des); %></span></h2>
			<h2>Requisições Obrigatórios: <span><%out.print(des); %></span></h2>
			<h2>Requisições Desejavéis: <span><%out.print(des); %></span></h2>
			<h2>Remuneração: <%out.print(rem); %></h2>
			<h2>Benefícios: <%out.print(ben); %></h2>
			<h2>Local: <%out.print(local); %></h2>
			<h2>Aberta: <%out.print(aberta); %></h2>
	</div>
	<a class="btn btn-info" href="/BDVagas/listar.jsp">Voltar</a>


</body>
</html>