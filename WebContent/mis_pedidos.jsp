<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis pedidos</title>
<link rel="stylesheet" type="text/css" href="CSS/estilos.css" />
</head>
<body>

<!--CABECERA -->
	<%@ page
		import="modelo.Productos,modelo.Usuarios, modelo.ModeloProductos, modelo.ProductoCarrito,modelo.ModeloCarrito,modelo.Pedidos,java.util.* "%>
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


		<div id="content" style="height: 850px;">
			
			
			<%
				Pedidos[] p = null;
				//ahora vamos a pedir el array e usuario y el metodo del servlet
				p = (Pedidos[]) request.getAttribute("Mis_pedidos");
			%>



			<div id ="central">
			<table style="border: solid">
				
				<tr>
					<th>Imagen</th>
					<th>Nombre</th>
					<th>Unidades</th>
					<th>Precio(€)</th>
					<th>Total</th>
				</tr>
							
				<%
					for (Pedidos pro : p) {
				%>
				<tr>
					<td><img style="width:50px" src='bajar?codigo=<%=pro.getId() %>'/></td>
					<td><%=pro.getNombre() %></td>
					<td><%=pro.getUnidades() %></td>
					<td><%=pro.getPrecio() %></td>
					<td><%=pro.getTotal() %></td>
				</tr>
				
				<%} %>
			</table>
			</div>




			


		


		</div>

		<footer id="footer">
		
			<p>Tienda web Daneri Alvarez &copy;</p>
			
		</footer>

	</div>



</body>
</html>