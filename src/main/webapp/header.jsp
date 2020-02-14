<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="es">
<!-- this template was designed by http://www.tristarwebdesign.co.uk - please visit for more templates & information - thank you. -->
<head>
<title>Fans de las aves chilenas (SWSEC Intro)</title>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

<div id="border">
	<div id="container">
		
		<div id="menu">
			<ul>
				<li><a title="Home" id="home" href="index.jsp">Home</a></li><li>|</li>
				<% if (session.getAttribute("username") == null) { %>
				<li><a title="Ingresar" id="login" href="login.jsp">Ingresar</a></li><li>|</li>
				<% } %>
				<li><a title="Saludos" id="hello" href="saludos.jsp">Saludos</a></li>
				<% if (session.getAttribute("username") != null) { %>
				<li>|</li>
				<li><a title="Muro" id="wall" href="Wall">Muro</a></li><li>|</li>
				<li><a title="Imagenes" id="images" href="pictures.jsp">Imagenes</a></li><li>|</li>
				<li><a title="Usuarios" id="users" href="ListaUsuarios?type=2">Usuarios</a></li><li>|</li>
				<li><a title="Cuenta" id="account" href="cuenta.jsp">Cuenta</a></li><li>|</li>
				<% if (session.getAttribute("isAdmin").equals("true")) { %>
				<li><a title="Administración" id="admin" href="Admin">Administraci&oacute;n</a></li><li>|</li>
				<% } %>
				<li><a title="Salir" id="exit" href="Exit">Salir</a></li>
				<% } %>
			</ul>
		</div>
	
		<div id="header">
			<p><a title="website name" href="index.jsp">Fans de las aves chilenas</a></p>
		</div>
		
		<div id="content">
