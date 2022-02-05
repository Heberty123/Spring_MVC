<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./resource/css/estilo.css">
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


int number = 2;
String abertura = request.getParameter("abertura");
if(abertura != null){
	number = Integer.parseInt(abertura);
}

%>
<h1>Lista de Vagas</h1>
<h2>Deseja que vagas ? <a href="/BDVagas/listar.jsp?abertura=1" class="btn btn-primary">Abertas</a> <a href="/BDVagas/listar.jsp?abertura=0" class="btn btn-danger">Encerradas</a> <a class="btn btn-success" href="/BDVagas/listar.jsp?abertura=2" class="btn btn-danger">Todas</a></h2>





	<div class="row">
		<div class="col-12">
			<table class="table table-hover"">
				<thead>
					<th scope="col">ID</th>
					<th scope="col">Descrição</th>
					<th scope="col">Req.Obrigatorios</th>
					<th scope="col">Req.Desejavéis</th>
					<th scope="col">Remuneração</th>
					<th scope="col">Benefícios</th>
					<th scope="col">Local</th>
					<th scope="col">Aberta</th>
					<th scope="col">Apagar</th>
					<th scope="col">Editar</th>
				</thead>
				<tbody>
					<% for(Vagas var: banco.listar(number)){ %>
					
					<tr>
						<th class="col-1" scope="row">Id: <% out.println(var.getId()); %></th>
						<td class="col-4"><% out.println(var.getDescricao()); %></td>
						<td class="col-2"><% out.println(var.getRequisitosObrigatorios()); %></td>
						<td class="col-2"><% out.println(var.getRequisitosDesejaveis()); %></td>
						<td class="col-1"><% out.println(var.getRemuneracao()); %></td>
						<td class="col-1"><% out.println(var.getBeneficio()); %></td>
						<td class="col-2"><% out.println(var.getLocalDeTrabalho()); %></td>
						<td class="col-1"><% out.println(var.getAberta()); %></td>
						<td class="col-1"><a class="btn btn-warning" href="./excluir.jsp?id=<%out.println(var.getId());%>">Apagar </a></td>
						<td class="col-1"><a class="btn btn-light" href="./editar.jsp?id=<%out.println(var.getId());%>"> Editar</a></td>
						
					
					</tr>
				
					<%
					}
					%>
				</tbody>
			
			</table>
			
		<%-- 
			<h1>Id: <% out.println(var.getId()); %><a href="./excluir.jsp?id=<%out.println(var.getId());%>">Apagar </a><a href="./editar.jsp?id=<%out.println(var.getId());%>"> Editar</a></h1>
			<p>Descrição: <% out.println(var.getDescricao()); %></p>
			<h2>Re.Obrigatórios: <% out.println(var.getRequisitosObrigatorios()); %></h2>
			<h2>Re.Desejaveis: <% out.println(var.getRequisitosDesejaveis()); %></h2>
			<h2>Remuneração: <% out.println(var.getRemuneracao()); %></h2>
			<h2>Benefícios: <% out.println(var.getBeneficio()); %></h2>
			<h2>Local de Trabalho: <% out.println(var.getLocalDeTrabalho()); %></h2>
			<h2>Aberta: <% out.println(var.getAberta()); %></h2>
			
		 --%>
		 
		 
		
		</div>
	</div>


</body>
</html>