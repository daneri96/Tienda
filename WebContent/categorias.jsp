<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categorias tienda</title>
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
				<li><a href="#">Categoria 1</a></li>
				<li><a href="#">Categoria 2</a></li>
				<li><a href="#">Categoria 3</a></li>
				<li><a href="#">Carrito</a></li>

			</ul>
		</nav>
		
		<div id="content">
			<aside id="lateral">
				<div id="login" class="block_aside">

					<h3>Entrar a la web</h3>

					<form action="Inicio" method="post">
						<label for="email">correo</label> <input type="email" name="email" />
						<label for="password">contraseña</label> <input type="password"
							name="password" /> <input type="submit" value="acceso"
							name="pagename" />
					</form>


					<form action="Categorias" method="get">
						<input type="submit" value="Gestionar Categorias" />
					</form>

					<form action="Productos" method="get">
						<input type="submit" value="Gestionar Productos" />
					</form>

					<form action="Inicio" method="post">
						<input type="submit" value="Cerrar sesion" name="pagename" />
					</form>

					<ul>
						<li><a href="#">Gestionar pedidos</a></li>
						<li><a href="#">Mis pedidos</a></li>
					</ul>

					<ul>
						<li><a href="MostrarRegistro">Registrate aqui</a></li>
					</ul>

				</div>
			</aside>
		
			<div id="central">
				<a href="Formulario_Categoria" class="button button-small"
					style="width: 120px; text-align: center; margin-bottom: 20px">Crear
					categoria</a>
			</div>
		
		</div>
		
		
		<footer id="footer">
			<p>Tienda web Daneri Alvarez &copy;</p>
		</footer>
		
	</div>

</body>
</html>