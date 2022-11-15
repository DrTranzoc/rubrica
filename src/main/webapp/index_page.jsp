<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css">

<link rel="stylesheet" href="css/basics.css">
<link rel="stylesheet" href="css/stylesheet_index.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript" src="js/serverRequests.js"></script>
<script type="text/javascript" src="js/indexHandler.js"></script>
<script type="text/javascript" src="js/navbar.js"></script>


<title>Lista contatti</title>
</head>
<body>

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
				Connettiti al DB </h2>
			<div>
				<div >
					<div class="form-group">
						<label for="host">Host</label> <input type="text"
							class="form-control" id="host" maxlength="30" placeholder="Nome dell'host"
							required>
						<label for="porta">Porta</label> <input type="number"
							class="form-control" id="porta" placeholder="3306"
							maxlength="20" required>
					<div class="form-group">
						<label for="user">User</label> <input type="text"
							class="form-control" id="user" maxlength="45" placeholder="username"
							>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="password"
							class="form-control" id="password" maxlength="45"
							>
					</div>
					<hr class="rounded">
					<div class="submit_wrapper">
						<button onclick="connettiDB()" style="margin-top: 2%" class="btn btn-primary">Connettiti</button>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>