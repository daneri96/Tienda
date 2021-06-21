package modelo;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ModeloProductos {

	// PAGINAR LOS PRODUCTOS SIMPLES POR CATEGORIAS hacemos uso del OFFSET y controlamos el limite de productos a mostrar y el tamaño de página
	public Productos[] paginarRopa(Usuarios usu, int categoria, int pag, int pagsize) {

		Productos[] p = null;

		try {
			Statement st = usu.getConn().getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from \"tienda\".productos where categoria_id =" + categoria
					+ " OFFSET " + pag * pagsize + " limit " + pagsize);
			int rowcount = 0, i = 0;

			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();

			}

			p = new Productos[rowcount];

			while (rs.next())
				p[i++] = new Productos(rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("id"));

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		return p;

	}

	public int numeroPaginas(Usuarios usu, int pagsize) {

		int num = 0;
		try {
			Statement st = usu.getConn().getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select count(*) from \"tienda\".productos ");

			rs.next();
			num = rs.getInt(1) / pagsize;

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		return num;

	}

	// PAGINAR LOS PRODUCTOS DE DESTACADOS CON UN VALOR MAYOR A 50 EUROS
	public Productos[] paginarDestacados(Usuarios usu, int precio, int pag, int pagsize) {

		Productos[] p = null;

		try {
			Statement st = usu.getConn().getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from \"tienda\".productos where precio > " + precio
					+ " OFFSET " + pag * pagsize + " limit " + pagsize);

			int rowcount = 0, i = 0;

			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();
			}

			p = new Productos[rowcount];

			while (rs.next())
				p[i++] = new Productos(rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("id"),
						rs.getBytes("imagen"));

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		return p;

	}

	// MOSTRAR LOS PRODUCTOS LISTADOS EN UNA TABLA PARA LA ZONA DEL ADMINSTRADOR

	public Productos[] mostrarProductos(Usuarios usu) {

		Productos[] p = null;

		try {
			Statement st = usu.getConn().getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select id,nombre,descripcion,precio,stock from \"tienda\".productos ");
			int rowcount = 0, i = 0;

			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();

			}

			p = new Productos[rowcount];

			while (rs.next())
				p[i++] = new Productos(rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("id"),
						rs.getInt("stock"), rs.getString("descripcion"));

		} catch (SQLException ex) {

			ex.printStackTrace();

		}

		return p;

	}

	// Insertar productos desde el administrador
	public boolean InsertarProducto(String nombre, String descripcion, double precio, int stock, int categoria_id) {
		Conexion conn;
		conn = new Conexion("ns3034756.ip-91-121-81.eu:5432", "a20-dalvcor", "a20-dalvcor", "a20-dalvcor");



		String sql = "INSERT INTO \"tienda\".productos (nombre,descripcion,precio,stock,categoria_id,fecha)  VALUES(?,?,?,?,?,?,CURRENT_DATE)";
		boolean resultado = false;

		try {
			PreparedStatement st = conn.getConexion().prepareStatement(sql);
			st.setString(1, nombre);
			st.setString(2, descripcion);
			st.setDouble(3, precio);
			st.setInt(4, stock);
			st.setInt(5, categoria_id);
			st.executeUpdate();
			st.close();
			resultado = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return resultado;
	}

}
