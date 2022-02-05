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

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

	<div class="container">
		<h1>Cadastre uma vaga !</h1>


		<form action="/BDVagas/listar.jsp" method="Post">
			<div class="mb-3">
				<label class="form-label" for="des">Descricao: </label>
				<input class="form-control" type="text" name="des" id="des"><br>
			</div>
			<div class="mb-3">
				<label class="form-label" for="reqO">Requisições Obrigatórias: </label>
				<input class="form-control" type="text" name="reqO" id="reqO"><br>
			</div>
			<div class="mb-3">
				<label class="form-label" for="reqD">Requisições Desejaveis: </label>
				<input class="form-control" type="text" name="reqD" id="reqD"><br>		
			</div>
			<div class="mb-3">
				<label class="form-label" for="rem">Remuneração: </label>
				<input class="form-control" type="text" name="rem" id="rem"><br>		
			</div>
			<div class="mb-3">
				<label class="form-label" for="ben">Beneficios: </label>
				<input class="form-control" type="text" name="ben" id="ben"><br>			
			</div>
			<div class="mb-3">
				<label class="form-label" for="local">Local de trabalho: </label>
				<input class="form-control" type="text" name="local" id="local"><br>		
			</div>
			<div class="mb-3">
				<label class="form-label" for="aberta">Aberta: </label>
				<input class="form-control" type="text" name="aberta" id="aberta"><br>
			</div>
			<div class="mb-3">
				<input type="submit" value="enviar">
			</div>
		</form>
	</div>

</body>
</html>