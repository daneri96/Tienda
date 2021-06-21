<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mostrar Productos</title>
<link rel="stylesheet" type="text/css" href="CSS/estilos.css" />
</head>
<body>
<%@ page
		import="modelo.Productos"%>
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

			<aside id="lateral">
				<div id="login" class="block_aside">

					<%
						if (session.getAttribute("inicio") != null) {
					%>
					<!-- ETIQUETA JSP QUE MUESTRA NOMBRE Y APELLIDOS DEL USUARIO QUE ACCEDE A LA P�GINA -->
					<h3><%=session.getAttribute("inicio")%></h3>
					<%
						} else if (session.getAttribute("inicio") == null) {
					%>

					<%
						if (session.getAttribute("error") != null) {
					%>
					<strong class="alert_red"><%=session.getAttribute("error")%></strong>
					<%
						}
					%>

					<form action="AccesoUsuarios" method="post">
						<label for="email">correo</label> <input type="email"
							name="correo" /> <label for="password">contraseña</label> <input
							type="password" name="contrasena" /> <input type="submit"
							value="acceso" name="pagename" />
					</form>

					<%
						}
					%>



				<form action="MostrarCategorias" method="get"
						style="display:<%=(session.getAttribute("admin") != null && session.getAttribute("admin").equals("admin")) ? "Block"
					: "None"%>">
						<input type="submit" value="Gestionar Categorias" />
					</form>

					<form action="MostrarProductos" method="get"
						style="display:<%=(session.getAttribute("admin") != null && session.getAttribute("admin").equals("admin")) ? "Block"
					: "None"%>">
						<input type="submit" value="Gestionar Productos" />
					</form>

					<form action="AccesoUsuarios" method="post"
						style="display:<%=(session.getAttribute("admin") != null) ? "Block" : "None"%>">
						<input type="submit" value="Cerrar sesion" name="pagename" />
					</form>

					<form
						style="display:<%=(session.getAttribute("admin") != null && session.getAttribute("admin").equals("user")) ? "Block"
					: "None"%>"
						action="AccesoUsuarios" method="POST">
						<input type="submit" value="Mostrar Perfil" name="pagename" />
					</form>

					<ul
						style="display:<%=(session.getAttribute("admin") != null && session.getAttribute("admin").equals("user")) ? "Block"
					: "None"%>">
						<li><a href="#">Gestionar pedidos</a></li>
						<li><a href="#">Mis pedidos</a></li>
						<li><a href="EliminarCuenta">EliminarCuenta</a></li>
					</ul>


					<ul
						style="display:<%=(session.getAttribute("admin") == null) ? "Block" : "None"%>">
						<li><a href="MostrarRegistro ">Registrate aqui</a></li>
					</ul>

				</div>
			</aside>


			<div id="central">
				<a href="Crear_Producto" class="button button-small"
					style="width: 120px; text-align: center; margin-bottom: 20px">Crear
					Producto</a>
					
					
					<h2>Muestra de productos</h2>
					
					
					<table>
						<tr>
							<th>ID_PRODUCTO</th>
							<th>NOMBRE</th>
							<th>DESCRIPCION</th>
							<th>PRECIO</th>
							<th>STOCK</th>
						</tr>
					
							<%
								Productos[] pr = null;
								//ahora vamos a pedir el array e usuario y el metodo del servlet
								pr = (Productos[]) request.getAttribute("Productos");
								
							%>
								
							<%
							 for(Productos p :pr){
							
							%>
					<tr>
					
							<td><%=p.getId() %></td>
							<td><%=p.getNombre() %></td>
							<td><%=p.getDescripcion() %></td>
							<td><%=p.getPrecio() %></td>
							<td><%=p.getStock() %></td>
					</tr>
							<%
							
							 }
							
							%>
							
					
					</table>
					
					
					
					
					
					
					
			</div>
			
			

		</div>

		<footer id="footer">
			<p>Tienda web Daneri Alvarez &copy;</p>
		</footer>


	</div>
</body>
</html>