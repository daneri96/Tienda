package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ModeloUsuarios {

	
	///ns3034756.ip-91-121-81.eu:5432/a20-dalvcor","a20-dalvcor","a20-dalvcor"
			//queda guardado para posibles cambios
		 	Usuarios usu;
			private Conexion conn;
			
			public ModeloUsuarios() {
				conn = new Conexion("ns3034756.ip-91-121-81.eu:5432","a20-dalvcor","a20-dalvcor","a20-dalvcor");
				
			}
			
			
			//Registro de los usuarios
			
			public boolean RegistraUsuario(String nombre,String apellidos,String email,String password,String direccion) {
				String consulta ="INSERT INTO \"tienda\".usuarios (nombre,apellidos,email,password,direccion,rol,imagen)  VALUES(?,?,?,?,?,?,?)";
				boolean resultado = false;
				String rol ="user";
				
				File f = new File("user.jpg");
				FileInputStream fis;
				
				try {
					fis = new FileInputStream(f);
					
					
					PreparedStatement st = conn.getConexion().prepareStatement(consulta);
					st.setString(1, nombre);
					st.setString(2, apellidos);
					st.setString(3, email);
					st.setString(4, password);
					st.setString(5, direccion);
					st.setString(6, rol);
					st.setBinaryStream(7, fis, (int)f.length());
					st.executeUpdate();
					st.close();
					resultado  = true;
					
					
				
				}catch(SQLException ex) {
					ex.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return resultado;
			}
			

			public Usuarios loguear(Usuarios usu,String email,String password) {
				ResultSet rs;
				
				try {
					Statement st = usu.getConn().getConexion().createStatement();
				    rs = st.executeQuery("SELECT id,nombre,apellidos,email,password,direccion,rol FROM \"tienda\".usuarios where email= '"+email+"';");
				    
				    if(rs.next() && rs.getString("password").equals(password))
				    	usu = new Usuarios(rs.getInt("id"),rs.getString("nombre"),rs.getString("apellidos"),rs.getString("email"),rs.getString("password"),rs.getString("direccion"),rs.getString("rol"));
				    	
				    
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				return usu;
			}
			
			
			//comprobar si existe correo
			public boolean existeEmail(String email) {
				boolean resultado = false;
				ResultSet rs;
				try {
					Statement st = conn.getConexion().createStatement();
					String sql="SELECT email from \"tienda\".usuarios where email='"+email+"';";
					rs = st.executeQuery(sql);
				
					resultado = rs.next();
					
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				return resultado;
			}
			
			public boolean activacionUsuario(String email) {
				String consulta ="UPDATE \"tienda\".usuarios SET activado = true where email ='"+email+"'";
				boolean resultado = false;
				
				try {
					PreparedStatement st = conn.getConexion().prepareStatement(consulta);
					st.executeUpdate();
					st.close();
				
					resultado  = true;
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				return resultado;
			}

		//eliminarCuenta
			public boolean BajaCuenta(String email) {
				String consulta ="DELETE from \"tienda\".usuarios where email ='"+email+"' ";
				boolean resultado = false;
				try {
					PreparedStatement st = conn.getConexion().prepareStatement(consulta);
					st.executeUpdate();
					st.close();
				
					resultado = true;
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				return resultado;
			}
		
		//modificar usuario
		public boolean modificarUsuario(Usuarios aux) {
			boolean resultado = false;
			try 
			{
				Statement st = aux.getConn().getConexion().createStatement();
				st.execute("UPDATE \"tienda\".usuarios SET "+
						"nombre='"+aux.getNombre()+
						"', apellidos='"+aux.getApellidos()+
						"', email='"+aux.getEmail()+						
						"', direccion='"+aux.getDireccion()+
						"' WHERE id = '"+aux.getId()+"';");
				
				resultado = true;
				st.close();
				
			}catch(SQLException ex) {
				
				ex.printStackTrace();
				
			}
			return resultado;
		}
}
