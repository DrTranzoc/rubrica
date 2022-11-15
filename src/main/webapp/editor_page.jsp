<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" href="css/basics.css">
<link rel="stylesheet" href="css/stylesheet_editor.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css">



<script type="text/javascript" src="js/serverRequests.js"></script>
<script type="text/javascript" src="js/editorHandler.js"></script>
<script type="text/javascript" src="js/navbar.js"></script>

<title>Editor contatti</title>
</head>
<body>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="java.util.*,com.rubrica.data_repo.Persona"%>
	<%
	Integer id = -1;
	String nome = "";
	String cognome = "";
	String indirizzo = "";
	String telefono = "";
	Integer eta = 1;
	
	if (request.getAttribute("idContatto") != null){
		id = (Integer)request.getAttribute("idContatto");
		Persona contatto = (Persona)request.getAttribute("contatto");
		
		if(contatto != null){
			nome = contatto.getNome();
			cognome = contatto.getCognome();
			indirizzo = contatto.getIndirizzo();
			telefono = contatto.getTelefono();
			eta = contatto.getEta();
		}
	}
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


	<div class="page_box" id="mainbox" style="margin-top:100px">
		<div style="height: fit-content; width: auto; margin: 2%">
			<h2 id="contatto-header"
				style="font-family: 'Roboto', sans-serif; font-size: calc(20px + 1vw); font-weight: bold; color: #1d1d1d">
				Contatto <%= id!=-1 ? id : "" %></h2>
			<div>
				<div id="contattoID" contactId="<%= id %>">
					<div class="form-group">
						<label for="nome">Nome</label> <input type="text"
							class="form-control" id="nome" value="<%= nome %>" maxlength="20"
							required>
					</div>
					<div class="form-group">
						<label for="cognome">Cognome</label> <input type="text"
							class="form-control" id="cognome" value="<%= cognome %>"
							maxlength="20" required>
					</div>
					<div class="form-group">
						<label for="indirizzo">Indirizzo</label> <input type="text"
							class="form-control" id="indirizzo" value="<%= indirizzo %>"
							maxlength="50" required>
					</div>
					<div class="form-group">
						<label for="telefono">Telefono</label> 
						<input type="tel" class="form-control" id="telefono" value="<%= telefono %>" maxlength="15" required>
					</div>
					<div class="form-group">
						<label for="eta">Eta</label> 
						<input class="form-control" style="width:50%" type="number" id="eta" name="eta" min="1" max="150" maxlength="3" value="<%= eta %>" required>
					</div>
					<hr class="rounded">
					<div class="submit_wrapper" style="display:flex">
						<button style="margin-top: 2%" class="btn btn-primary"
							onclick="inviaContatto(<%= id == -1 ? 0 : 1 %>)">Salva</button>
						<form action="lista.jsp" style="margin-top: 2%;margin-left: 30px">
							<input type="submit" class="btn btn-primary" value="Annulla">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>