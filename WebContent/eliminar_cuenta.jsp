<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Eliminar Cuenta</title>
<link rel="stylesheet" type="text/css" href="CSS/estilos.css" />
</head>
<body>


	<div id="container">

		<jsp:include page="header.jsp"></jsp:include>

		<!-- MENU -->
		<nav id="menu">
			<ul>

				<li>
					<a href="Lanzadera"> Inicio </a>
				</li>
				
				<li>
					<form action="MostrarProductos" method="get">
						<input type="submit" value ="Manga Corta"/>
					</form>
				</li>
				<li>
					<form action="MostrarProductos" method="get">
						<input type="submit" value ="Tirantes"/>
					</form>
				</li>
				<li>
					<form action="MostrarProductos" method="get">
						<input type="submit" value ="Manga Larga"/>
					</form>
				</li>
				<li>
				
					<form action="MostrarProductos" method="get">
						<input type="submit" value ="Sudaderas"/>
					</form>
				
				</li>
			</ul>
		</nav>


		<div id="content" style="height:850px;">
		
					<%
					 if(session.getAttribute("exito") != null){
					%>
			 		<strong class="alert_green"><%=session.getAttribute("exito") %></strong>
			 		<%
					 }
			 		%>
			
			<h2 style="margin-left:23px;margin-top:35px;color:#222;">Eliminar Cuenta</h2>
			<h4 style="margin-left:23px;margin-top:7px;color:#222;">Si desea eliminar su cuenta por favor , introduzca su email</h4>
			<form style="width:28%;margin-left:25px;margin-top:10px;" action="EliminarCuenta" method="post">
				<input type="text" name="email" />
				<input type=submit name="pagename" value="Cancelar Cuenta"/>
			</form>
		</div>

		<footer id="footer">
			<p>Tienda web Daneri Alvarez &copy;</p>
		</footer>

	</div>

</body>
</html>