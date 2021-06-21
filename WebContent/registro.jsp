<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de usuario</title>
<link rel="stylesheet" type="text/css" href="CSS/estilos.css" />


</head>
<body>


	<!--CABECERA-->
	<div id="container">
		
		<jsp:include page="header.jsp"></jsp:include>

		
		<!-- MENU -->
		<nav id="menu">
			<ul>

				<li><a href="Lanzadera"> Inicio </a></li>
				<li>
					<form action="MostrarProductos" method="POST">
						<input type="submit" value="Manga Corta" name="pagename" />
					</form>
				</li>
				<li>
					<form action="MostrarProductos" method="POST">
						<input type="submit" value="Tirantes" name="pagename" />
					</form>
				</li>
				<li>
					<form action="MostrarProductos" method="POST">
						<input type="submit" value="Manga Larga" name="pagename" />
					</form>
				</li>
				<li>

					<form action="MostrarProductos" method="POST">
						<input type="submit" value="Sudaderas" name="pagename" />
					</form>

				</li>

			</ul>
		</nav>
		
		
		<div id="content">
		
	
			
			<div id="central">
				<h1>Registrarse</h1>
				
					<%
					 if(session.getAttribute("exito") != null){
					%>
			 		<strong class="alert_green"><%=session.getAttribute("exito") %></strong>
				 		
					<%}else if(session.getAttribute("exito") == null){  %>
							<strong></strong>
					<%}else{ %>
						<strong class="alert_red"><%=session.getAttribute("error") %></strong>
					<%} %>
				
					<%
						if(session.getAttribute("igual") !=null){
					%>
						<strong class="alert_red"><%=session.getAttribute("igual") %></strong>
					<%
						}
					%>
				
				<form action="RealizarRegistro" method="POST">

					<label for="nombre">Nombre</label> 
					<input type="text" name="nombre" required /> 
					<label for="apellidos">Apellidos</label> 
					<input	type="text" name="apellidos" required /> 
					<label for="email">Email</label>
					<input type="email" name="correo" required /> 
					<label for="password">Contraseña</label>
					<input type="password" name="contrasena" required />
					<label for="r_password">Repetir Contraseña</label>
					<input type="password" name="r_contrasena" required/>
					<label for="ciudad">Direccion</label>
					<input type="text" name="direccion" required/>
					<input type="submit" value="Registrarse" name="pagename"/>

				</form>
			
			
			</div>
		
		</div>
	</div>
	
		<footer id="footer">
			<p>Tienda web Daneri Alvarez &copy;</p>
		</footer>
	
	
</body>
</html>