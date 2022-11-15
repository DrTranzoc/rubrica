<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/basics.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="js/serverRequests.js"></script>
<script type="text/javascript" src="js/listaHandler.js"></script>
<script type="text/javascript" src="js/navbar.js"></script>

<title>Lista contatti</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*,com.rubrica.data_repo.Persona"%>
	<%
	List<Persona> contatti = (ArrayList<Persona>) request.getAttribute("contatti");
	%>

	<div class="topnav">
		<div>
			<a href="lista.jsp"> <img src="rubrica-icon.png"
				alt="Rubrica HOME" style="width: 64px; height: 64px;">
			</a>
		</div>
		<div class="nav-div">
			<a class="active" href="lista.jsp">Rubrica</a>
			<a onclick="cambiaDB()" href="#">Database</a>
		</div>
	</div>

	<div style="margin: auto; width: 50%; padding: 10px;">
		<div>
			<form action="editor.jsp" style="margin-bottom:20px">
				<input class="btn btn-primary" type="submit"
					value="Aggiungi Contatto">
			</form>
		</div>
		<table id="contact_table" class="table table-striped"
			style="width: 100%">
			<thead>
				<tr>
					<td style="font-weight: bold">ID</td>
					<td style="font-weight: bold">NOME</td>
					<td style="font-weight: bold">COGNOME</td>
					<td style="font-weight: bold">INDIRIZZO</td>
					<td style="font-weight: bold">TELEFONO</td>
					<td style="font-weight: bold">ETA</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ contatti }" var="contatto">
					<tr id="tr-${contatto.getId()}">

						<td>${ contatto.getId() }</td>
						<td>${ contatto.getNome() }</td>
						<td>${ contatto.getCognome() }</td>
						<td>${ contatto.getIndirizzo() }</td>
						<td>${ contatto.getTelefono() }</td>
						<td>${ contatto.getEta() }</td>
						<td>
							<button class="btn btn-primary" id="${contatto.getId()}"
								onclick="cancellaContatto(this)">CANCELLA</button>
						</td>
						<td>
							<button class="btn btn-primary" id="${contatto.getId()}"
								onclick="modificaContatto(this)">MODIFICA</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<hr>
	</div>

</body>
</html>