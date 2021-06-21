package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloPedidos {

	
	
	
	
	//mostrar los pedidos de los usuarios
	public Pedidos[] mostrarPedidos(Usuarios usu) {

		ResultSet rs;
		Pedidos[] p = null;

		try {
			Statement st = usu.getConn().getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String segundaConsulta = "select \"tienda\".productos.id,  nombre , unidades , \"tienda\".productos_pedido.precio ,unidades * \"tienda\".productos_pedido.precio\r\n" + 
					"as \"total\"  from \"tienda\".productos_pedido \r\n" + 
					"INNER JOIN \"tienda\".productos\r\n" + 
					"ON producto_id = id \r\n" + 
					"INNER JOIN \"tienda\".pedidos ON \"tienda\".pedidos.id  = \"tienda\".productos_pedido.pedido_id where usuario_id =" + usu.getId();
			rs = st.executeQuery(segundaConsulta);

			int rowcount = 0, i = 0;

			if (rs.last()) {
				rowcount = rs.getRow();
				rs.beforeFirst();

			}

			p = new Pedidos[rowcount];

			while (rs.next())
				p[i++] = new Pedidos(rs.getInt("id"),rs.getString("nombre"), rs.getInt("unidades"), rs.getDouble("precio"),
						rs.getDouble("total"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return p;
	}
}
