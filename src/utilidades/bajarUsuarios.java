package utilidades;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/bajarUsuarios")
public class bajarUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public bajarUsuarios() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection db;
		byte [] buf = null;
	

		String phototype = "jpeg";
		try {
		      // Load the driver
		      Class.forName("org.postgresql.Driver");
	//	System.err.println("1");
//		      Class.forName("org.firebirdsql.jdbc.FBDriver");

		      // Connect to database
		      db = DriverManager.getConnection("jdbc:postgresql://ns3034756.ip-91-121-81.eu/a20-dalvcor", "a20-dalvcor", "a20-dalvcor");
	//	System.err.println("2");
			
		// ID Find photos
			int codigo = 0;
			codigo = Integer.parseInt(request.getParameter("codigo"));
			String sentencia = "select imagen from \"tienda\".usuarios where id = "+codigo;
			Statement stmt = db.createStatement ();
			ResultSet rs = stmt.executeQuery (sentencia);
			
		// Image data is read into the buffer
			if (rs.next ()) {
				buf = rs.getBytes (1);
				
			}
			else {
				buf = new byte [0];
			}
		}
		catch (Exception e) {
		// Throw e;
		}

	// Response.setContentType (content_type);
	// Tells the browser to output pictures
		response.setContentType ("image /" + phototype);
	// Output image output stream
		OutputStream out = response.getOutputStream();
	// Output to the input of the buffer page
		out.write (buf);
	// Input is completed, clear buffer
		
		out.flush (); 
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
