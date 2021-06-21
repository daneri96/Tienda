package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloCategorias {

	
	//Mostrar las categorias para que el administrador pueda meter la categoria del producto
	public Categorias[] mostrarCategorias(Usuarios usu)
	{
		
		Categorias[] cat = null;
		
		try 
		{
			Statement st = usu.getConn().getConexion().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
				    ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select id,nombre from \"tienda\".categorias ");
			int rowcount = 0,i=0;
			
			if (rs.last()) 
			{
			  rowcount = rs.getRow();
			  rs.beforeFirst(); 
			 
			}
			
			
			cat = new Categorias[rowcount];
			
			while (rs.next())  cat[i++] = new  Categorias(rs.getInt("id"),rs.getString("Nombre"));
			  
			 
		
		}
		catch(SQLException ex) 
		{ 
			
			ex.printStackTrace();
		
		}
		
		return cat;
	
	}
}
