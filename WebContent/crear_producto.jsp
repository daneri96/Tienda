<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crear producto</title>
<link rel="stylesheet" type="text/css" href="CSS/estilos.css" />
</head>
<body>
<%@ page
		import="modelo.Categorias"%>

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
			<form action="CrearProducto" method="post">
				<label for="nombre">Nombre</label> <input type="text" name="nombre" />

				<label for="descripcion">Descripcion</label>
				<textarea name="descripcion"></textarea>

				<label for="precio">precio</label> <input type="text" name="precio" />

				<label for="stock">Stock</label> <input type="number" name="stock" />
					
				<%
					Categorias[] c = null;
					//ahora vamos a pedir el array e usuario y el metodo del servlet
					c = (Categorias[]) request.getAttribute("Categorias");
								
				%>
					<select name="categoria">
					<%
					for (Categorias cat : c) {
					%>
					<option value="<%=cat.getId() %>"><%=cat.getNombre() %></option>
					
					<%
					}
					%>
					
					</select>
					
					
				<label for="imagen">Imagen</label> <input type="file" name="imagen" />
				<input type="submit" value="Guardar" name="pagename" />

			</form>

		</div>
		
		<footer id="footer">
			<p>Tienda web Daneri Alvarez &copy;</p>
		</footer>

	</div>
	
</body>
</html>