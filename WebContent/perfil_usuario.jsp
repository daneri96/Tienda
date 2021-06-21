<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Datos Usuario</title>
<link rel="stylesheet" type="text/css" href="CSS/estilos.css" />
</head>
<body>

<!--CABECERA-->
<%@ page
		import="modelo.Usuarios,java.util.* "%>
	<div id="container">

		<jsp:include page="header.jsp"></jsp:include>

		<!-- MENU -->
		<nav id="menu">
			<ul>
			<!-- MOSTRAR PRODUCTOS POR EL POST CON LA PAGINACIÓN -->
				<li><a href="Lanzadera"> Inicio </a></li>
				
				<li>
					<form action="MostrarProductos" method="POST">
						<input type="submit" value ="Manga Corta" name="pagename"/>
					</form>
				</li>
				<li>
					<form action="MostrarProductos" method="POST">
						<input type="submit" value ="Tirantes" name="pagename"/>
					</form>
				</li>
				<li>
					<form action="MostrarProductos" method="POST">
						<input type="submit" value ="Manga Larga" name="pagename"/>
					</form>
				</li>
				<li>
				
					<form action="MostrarProductos" method="POST">
						<input type="submit" value ="Sudaderas" name="pagename"/>
					</form>
				
				</li>
			</ul>
		</nav>

		<div id="content" style="height:500px">
		
		
			<%
				Usuarios aux =(Usuarios)request.getSession().getAttribute("sesion");
			%>
			
			<h2
			style="margin-left:94px;margin-top:30px;text-transform:upppercase;text-decoration: underline #222; color:#222;"
			>Datos de Usuario</h2>
			
			
			<img style="width:300px;position:absolute;right:313px;top:294px;" src='bajarUsuarios?codigo=<%=aux.getId()%>'/>
			
		
			<form style="width:312px;margin-left:99px;margin-top:51px" action="ModificarUsuario" method="POST">
			
			
				<%
					if(session.getAttribute("modificado") != null){
				%>
					<strong class="alert_green"><%=session.getAttribute("modificado") %></strong>
				<%
					}	
				%>
				
			
				<label>Nombre:</label>
				<input type="text" value="<%= aux.getNombre() %>" name="nombre"/>
				
				<label>Apellidos:</label>
				<input type="text" value="<%=aux.getApellidos() %>" name="apellidos" />
				
				<label>Email:</label>
				<input type="text" value="<%= aux.getEmail() %>" name="email"/>
				
				<label>Direccion:</label>
				<input type="text" value ="<%= aux.getDireccion() %>" name="direccion"/>
				
				<input type="submit" name="pagename" value="Guardar Cambios"/>
 				
			</form>
			
			<form style="position:absolute;right:300px;" action="subir?codigo=<%=aux.getId() %>" method="post" enctype="multipart/form-data">
 					<input type=file size=60 name="file" value="Examinar"><br><br>
 					<input type=submit value="subir"><br>
			</form>
		
		</div>
		
		
		<footer id="footer">
			<p>Tienda web Daneri Alvarez &copy;</p>
		</footer>


	</div>

		



</body>
</html>