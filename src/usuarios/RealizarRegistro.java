package usuarios;

import java.io.IOException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.net.Inet4Address;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.ModeloUsuarios;
import modelo.Usuarios;
import utilidades.Correo;

@WebServlet("/RealizarRegistro")
public class RealizarRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

    public RealizarRegistro() {
        super();
        
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloUsuarios md;
		HttpSession sesion;
		md = new ModeloUsuarios();
		String control = request.getParameter("pagename");
		sesion = request.getSession();
		
		
		switch(control) 
		{
		//registro de usuario en el cual se realiza el envio de correo al usuario para verificar la cuenta
		case "Registrarse":
				String contrasena = request.getParameter("contrasena");
				String sha256 = getSHA256(contrasena);//además se codifica la contraseña en sha256
				String mensajeDeVerificacion="";
				
				if(!md.existeEmail(request.getParameter("correo"))) {
					
					if(md.RegistraUsuario(request.getParameter("nombre"), request.getParameter("apellidos"), request.getParameter("correo"), sha256, request.getParameter("direccion")) && request.getParameter("contrasena").equals(request.getParameter("r_contrasena"))) 
					{
						
						 sesion.setAttribute("exito", "Se le ha enviado un mensaje de confirmación al correo, para poder iniciar sesion");
						 Correo cor = new Correo();
						 mensajeDeVerificacion = "confirme su correo <a href='http://localhost:8080/JSP/ActivacionUsuario?correo="+request.getParameter("correo")+"'>Aquí</a>";
					     cor.enviar("danerialvarezcortes@gmail.com", "659867405l", request.getParameter("correo"), "link de activacion", mensajeDeVerificacion);
					     //realizamos el envio con el correo nuestro para que llegue al usuario el cual tendrá que activar su en enlace para activar la cuenta
						 response.sendRedirect("MostrarRegistro");
					}else 
					{
						sesion.setAttribute("error", "El registro no pudo ser completado correctamente");
						response.sendRedirect("MostrarRegistro");
					}
				}else {
					sesion.setAttribute("igual", "Ese correo ya existe");
					response.sendRedirect("MostrarRegistro");
				}
				
				
			
			break;
			
		}
		
		
		
	}
	//método para poer hacer que la contraseña sea codificada a SHA256
	public String getSHA256(String input) {
		String retorno = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			retorno = String.format("%064x", new BigInteger(1, digest.digest()));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
			return retorno;
		 
	}

}
