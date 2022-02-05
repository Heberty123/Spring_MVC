<%@page import="classes.Banco"%>
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

<%
Banco banco = new Banco();
String id_String = request.getParameter("id");
int id = 0;
Vagas vaga = null;
if(id_String != null){
	id = Integer.parseInt(id_String);
	vaga = banco.selectById(id);

%>

<h1>Editar vaga <%out.print(id);%>!</h1>


	<form action="/BDVagas/alterar.jsp?id=<%out.print(id);%>" method="Post">
		<label for="des">Descricao: </label>
		<input type="text" name="des" id="des" value="<%out.print(vaga.getDescricao());%>"><br>
		
		<label for="reqO">Requisições Obrigatórias: </label>
		<input type="text" name="reqO" id="reqO" value="<%out.print(vaga.getRequisitosObrigatorios());%>"><br>
		
		<label for="reqD">Requisições Desejaveis: </label>
		<input type="text" name="reqD" id="reqD" value="<%out.print(vaga.getRequisitosDesejaveis());%>"><br>
		
		<label for="rem">Remuneração: </label>
		<input type="text" name="rem" id="rem" value="<%out.print(vaga.getRemuneracao());%>"><br>
		
		<label for="ben">Beneficios: </label>
		<input type="text" name="ben" id="ben" value="<%out.print(vaga.getBeneficio());%>"><br>
		
		<label for="local">Local de trabalho: </label>
		<input type="text" name="local" id="local" value="<%out.print(vaga.getLocalDeTrabalho());%>"><br>
		
		<label for="aberta">Aberta: </label>
		<input type="text" name="aberta" id="aberta" value="<%out.print(vaga.getAberta());%>"><br>
		
		<input type="submit" value="enviar">
	</form>
<%}
else {

%>

	<h2>Para editar algumas vagas, vai para lista <a href="/BDVagas/listar.jsp">editar</a> </h2>




<%} %>

</body>
</html>