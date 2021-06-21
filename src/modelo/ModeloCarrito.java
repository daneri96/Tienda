package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloCarrito {

	/*realizamos la insercción de carrito en caso de que el usuario este registrado y lo añadimos en base de datos
	 * para esto comprobamos que halla algo en el caro para insertar y hacer modificaciones 
	 * usamos siempre la conexión del usuario.
	 */
	public boolean insertarCarrito(Usuarios usu, int codigo) {
		boolean resultado = false;
		int cantidad = productoEnCarrito(usu, codigo);//comprobamos si hay algo en carrito

		if (cantidad != -1) {
			String consulta = "UPDATE \"tienda\".carrito SET cantidad =?   WHERE usuario_id =? and producto_id= ?";

			try {

				PreparedStatement st = usu.getConn().getConexion().prepareStatement(consulta);
				st.setInt(1, cantidad + 1);

				st.setInt(2, usu.getId());
				st.setInt(3, codigo);

				st.executeUpdate();
				st.close();
				resultado = true;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			String consulta = "INSERT INTO \"tienda\".carrito (usuario_id,producto_id,cantidad)  VALUES(?,?,?)";

			try {

				PreparedStatement st = usu.getConn().getConexion().prepareStatement(consulta);
				st.setInt(1, usu.getId());
				st.setInt(2, codigo);
				st.setInt(3, 1);
				st.executeUpdate();
				st.close();
				resultado = true;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return resultado;

	}

	public int productoEnCarrito(Usuarios usu, int codigo) {
		int resultado = -1;
		ResultSet rs;
		try {
			Statement st = usu.getConn().getConexion().createStatement();
			String segundaConsulta = "select cantidad from \"tienda\".carrito where usuario_id =" + usu.getId()
					+ " and producto_id= " + codigo + "";
			rs = st.executeQuery(segundaConsulta);

			if (rs.next())
				resultado = rs.getInt(1);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return resultado;
	}

	public boolean eliminarCarrito(Usuarios usu, int codigo) {
		boolean resultado = false;
		int cantidad = productoEnCarrito(usu, codigo);

		if (cantidad == 1) {
			String consulta = "DELETE from \"tienda\".carrito   WHERE usuario_id =? and producto_id= ?";

			try {

				PreparedStatement st = usu.getConn().getConexion().prepareStatement(consulta);
				st.setInt(1, usu.getId());
				st.setInt(2, codigo);

				st.executeUpdate();
				st.close();
				resultado = true;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			String consulta = "UPDATE \"tienda\".carrito SET cantidad = ? WHERE usuario_id =? and producto_id= ?";

			try {

				PreparedStatement st = usu.getConn().getConexion().prepareStatement(consulta);
				st.setInt(1, cantidad - 1);
				st.setInt(2, usu.getId());
				st.setInt(3, codigo);
				st.executeUpdate();
				st.close();
				resultado = true;
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return resultado;

	}

	// mostrar carrito
	public ProductoCarrito[] mostrarEnCarrito(Usuarios usu) {

		ResultSet rs;
		ProductoCarrito[] p = null;

		try {
			Statement st = usu.getConn().getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String segundaConsulta = "select id, nombre , precio  , cantidad"
					+ " from \"tienda\".carrito INNER JOIN \"tienda\".productos"
					+ " ON producto_id = id  where usuario_id =" + usu.getId();
			rs = st.executeQuery(segundaConsulta);

			int rowcount = 0, i = 0;

			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();

			}

			p = new ProductoCarrito[rowcount];

			while (rs.next())
				p[i++] = new ProductoCarrito(rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("id"),
						rs.getInt("cantidad"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return p;
	}

	// Procesar Carrito

	public boolean procesarCarrito(Usuarios usu) {
		
		boolean resultado = false;
		String consulta = "INSERT INTO \"tienda\".pedidos (usuario_id,coste,momento_compra)  VALUES(?,0,CURRENT_DATE)";
		//INSERT INTO "tiendaRopa".pedidos (usuario_id,momento_compra)  VALUES(13,CURRENT_DATE);
		
		try {
			PreparedStatement st = usu.getConn().getConexion().prepareStatement(consulta);
			st.setInt(1, usu.getId());
			st.executeUpdate();
			st.close();
			resultado = true;
		
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		
		return resultado;
		
	}

}
