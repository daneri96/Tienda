package usuarios;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ModeloUsuarios;
import modelo.Usuarios;

@WebServlet("/ModificarUsuario")
public class ModificarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ModificarUsuario() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.getRequestDispatcher("perfil_usuario.jsp").forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String control = request.getParameter("pagename");
		Usuarios aux =(Usuarios)request.getSession().getAttribute("sesion");
		ModeloUsuarios mod = new ModeloUsuarios();
		HttpSession sesion;
		sesion = request.getSession();
		
		switch(control) 
		{
		case "Guardar Cambios":
				aux.setNombre(request.getParameter("nombre"));
				aux.setApellidos(request.getParameter("apellidos"));
				aux.setEmail(request.getParameter("email"));
				aux.setDireccion(request.getParameter("direccion"));
				
				
				if(mod.modificarUsuario((Usuarios)request.getSession().getAttribute("sesion"))) {
					sesion.setAttribute("modificado", "Los cambios fueron realizados con exito");
					response.sendRedirect("ModificarUsuario");
					
				}
				
			break;
		case "Mostrar Perfil":
			request.getRequestDispatcher("perfil_usuario.jsp").forward(request, response);
		break;
		}
	}

}
