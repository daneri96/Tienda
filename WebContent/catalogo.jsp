<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function mostrar() {
		document.getElementById("carrito").style.display = document
				.getElementById("carrito").style.display == 'block' ? 'none'
				: 'block';
	}
</script>
<meta charset="utf-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="CSS/estilos.css" />
</head>
<body>
	<%@ page
		import="modelo.Productos, modelo.ModeloProductos,modelo.ProductoCarrito,modelo.ModeloCarrito,modelo.Usuarios,java.util.* "%>
	<!--CABECERA-->
	<div id="container">

		<jsp:include page="header.jsp"></jsp:include>
		
		<!-- MENU -->
		<nav id="menu">
			<ul>
				<!-- MOSTRAR PRODUCTOS POR EL POST CON LA PAGINACIÓN -->
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
			<aside id="lateral">
				<div id="login" class="block_aside">

					<h3>Entrar a la web</h3>

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


					<input type="button" value="Carrito" onclick="mostrar()"
						name="pagename" />
					<div
						style="display:<%=((boolean) request.getAttribute("MostrarCarrito")) ? "Block" : "None"%> ; width: 400px; height: 500px; position: absolute; background: red; top: 110px; left: 145px"
						id="carrito" class="default">

						<table style="border: solid">
							<tr>
								<th>Imagen</th>
								<th>Nombre</th>
								<th>Precio</th>
								<th>Unidades</th>
								<th>Eliminar</th>
								<th>Añadir</th>
							</tr>
							<%
								ProductoCarrito[] pr = null;
								//ahora vamos a pedir el array e usuario y el metodo del servlet
								Usuarios aux = (Usuarios)request.getSession().getAttribute("sesion");
								if ( aux.getNombre().equals("anonimo") )
									pr = (ProductoCarrito[]) request.getSession().getAttribute("Carrito");
								else
									pr = (ProductoCarrito[]) request.getAttribute("ProductosCarrito");
								double total = 0;
							%>
				

							<%
								if(pr!=null)
								for (ProductoCarrito pc : pr) {
							%>
							<tr>
								<td><img style="width:59px" src='bajar?codigo=<%=pc.getId() %>'/></td>
								<td><%=pc.getNombre()%></td>
								<td><%=Math.round(pc.getPrecio())%></td>
								<td><%=pc.getCantidad()%></td>
								<%
								total +=  pc.getPrecio() * pc.getCantidad();	
								%>
								<td>
									<form action="MostrarProductos" method="POST">
										<input type="text" value="<%=pc.getId()%>" name="id_producto"
											style="display: none;" /> <input type="submit" value="-"
											name="pagename" />
									</form>
								</td>
								<td>
									<form action="MostrarProductos" method="POST">
										<input type="text" value="<%=pc.getId()%>" name="id_producto"
											style="display: none;" /> <input type="submit" value="+"
											name="pagename" />
									</form>
								</td>

							</tr>


							<%
								}
							%>
							<tr>
							
							<tr>
							<td style="color:black;">TOTAL</td>
							<td><%=Math.round(total) %></td>
							</tr>



						</table>
						
							<% if (session.getAttribute("comprado") != null){ %>
							<strong class="alert_green"><%=session.getAttribute("comprado") %></strong>
							
						<%} else {%>
								<strong class="alert_red"><%=session.getAttribute("no_comprado") %></strong>
						<%} %>
						
						<form action="MostrarProductos" method="post">
						
						
						<%
								if ( !aux.getNombre().equals("anonimo") )
								{
						%>
						
							<input type='submit' value='Aceptar Compra' name='pagename'>
							
						<%
								}else{
						%>
							<input type='submit' value='Acceda para procesar la compra.' name='boton' disabled>
						<%
								}
						%>	
							
						</form>
						<input type="button" value="Cerrar Carrito" onclick="mostrar()"
							name="pagename" />
					</div>



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

					<ul>
						<li><a href="MostrarRegistro">Registrate aqui</a></li>
					</ul>
				</div>
			</aside>





			<%
				Productos[] p = null;
				//ahora vamos a pedir el array e usuario y el metodo del servlet
				p = (Productos[]) request.getAttribute("Productos");
			%>




			<%
				for (Productos pro : p) {
			%>
			
			
			<div class="product">

				<form action="MostrarProductos" method="post">
					<img src='bajar?codigo=<%=pro.getId() %>' /><br> Código:<input
						type="text" value="<%=pro.getId()%>" name="codigo_id" readonly />
					Nombre:<input type="text" value="<%=pro.getNombre()%>"
						name="nombre" readonly /> Precio(€):<input type="text"
						value="<%=pro.getPrecio()%> " name="precio" readonly /> <input
						type="submit" name="pagename" value="Add Carrito" />
				</form>

			</div>

		
			<%
				}
			%>


			<div id=botones>

				<form action="MostrarProductos" method="post"
					style="position: absolute; right: 189px; top: 970px;">
					<input type="submit" value="Siguiente" name="pagename" />
				</form>

				<form action="MostrarProductos" method="post"
					style="position: absolute; right: 896px; top: 970px;">
					<input type="submit" value="Atras" name="pagename" />
				</form>

			</div>



		</div>



		<footer id="footer">
			<p>Tienda web Daneri Alvarez &copy;</p>
		</footer>

	</div>


</body>
</html>