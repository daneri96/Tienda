package usuarios;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import modelo.ModeloCarrito;
import modelo.ModeloUsuarios;
import modelo.ProductoCarrito;
import modelo.Usuarios;


@WebServlet("/AccesoUsuarios")
public class AccesoUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
    public AccesoUsuarios() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloUsuarios md;
		Usuarios aux;
		HttpSession sesion;
		md = new ModeloUsuarios();
		
		String control = request.getParameter("pagename");
		sesion = request.getSession();
		
		switch(control)
		{
		case "acceso":
			 String contrasena = request.getParameter("contrasena");
			 String sha256 = getSHA256(contrasena);
			 aux = (Usuarios) md.loguear((Usuarios)request.getSession().getAttribute("sesion"),request.getParameter("correo"),sha256);
			
				if (!aux.equals("anonimo")) //si aux es distinto de nulo
				{
					
					//seteamos la sesion con el objeto aux
					sesion.setAttribute("inicio", aux.getNombre());
					sesion.setAttribute("sesion", aux); 
					sesion.setAttribute("admin", aux.getRol());
					response.sendRedirect("Lanzadera");
					
					//tiene carrito como anónimo, se añade al carrito del usuario en BD
					ProductoCarrito[] p =(ProductoCarrito[]) request.getSession().getAttribute("Carrito");
					if(p!=null)
					{
						ModeloCarrito mod = new ModeloCarrito();
						for(ProductoCarrito pc : p)
							for(int i = 0; i < pc.getCantidad();i++)
								mod.insertarCarrito(aux, pc.getId());
					}
					request.getSession().setAttribute("Carrito",null);			
				}
				else
				{
					sesion.setAttribute("error","Datos, incorrectos");
					response.sendRedirect("Lanzadera");
				}
		break;
		
	
		
	
		
		case "Cerrar sesion":
			sesion.setAttribute("inicio",null); 
			sesion.setAttribute("admin", null);
			response.sendRedirect("Lanzadera");
		break;
		
		}
	}
	
	
	
	
	
	
	
	
	public  String getSHA256(String input) {
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
