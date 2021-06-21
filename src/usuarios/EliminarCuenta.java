package usuarios;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ModeloUsuarios;
import utilidades.Correo;


@WebServlet("/EliminarCuenta")
public class EliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EliminarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("eliminar_cuenta.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloUsuarios md = new ModeloUsuarios();
		String control = request.getParameter("pagename");
		String mensajeDeVerificacion="";
		HttpSession sesion;
		
		sesion = request.getSession();
		
		switch(control) {
		case "Cancelar Cuenta":
			 if(md.existeEmail(request.getParameter("email"))) 
			 {
				 Correo cor = new Correo();
				 mensajeDeVerificacion = "confirme su baja de correo aqui <a href='http://localhost:7070/JSP/CancelarCuenta?email="+request.getParameter("email")+"'>Aquí</a>";
			     cor.enviar("danerialvarezcortes@gmail.com", "659867405l", request.getParameter("email"), "link de baja de cuenta", mensajeDeVerificacion);
			     sesion.setAttribute("exito", "Le ha llegado un correo para que confirme su baja, una vez activado sera dado de baja gracias.");
			     response.sendRedirect("EliminarCuenta");
			 }
			
			break;
			
		}
	}

}
