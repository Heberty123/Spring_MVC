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

<h1>Cadastre uma vaga !</h1>


	<form action="/BDVagas/listar.jsp" method="Post">
		<label for="des">Descricao: </label>
		<input type="text" name="des" id="des"><br>
		
		<label for="reqO">Requisições Obrigatórias: </label>
		<input type="text" name="reqO" id="reqO"><br>
		
		<label for="reqD">Requisições Desejaveis: </label>
		<input type="text" name="reqD" id="reqD"><br>
		
		<label for="rem">Remuneração: </label>
		<input type="text" name="rem" id="rem"><br>
		
		<label for="ben">Beneficios: </label>
		<input type="text" name="ben" id="ben"><br>
		
		<label for="local">Local de trabalho: </label>
		<input type="text" name="local" id="local"><br>
		
		<label for="aberta">Aberta: </label>
		<input type="text" name="aberta" id="aberta"><br>
		
		<input type="submit" value="enviar">
	</form>

</body>
</html>