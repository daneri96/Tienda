package productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ModeloProductos;
import modelo.Usuarios;



@WebServlet("/GestionarProductos")
public class GestionarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GestionarProductos() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProductos md = new ModeloProductos();
		
	    request.setAttribute("Productos", md.mostrarProductos((Usuarios)request.getSession().getAttribute("sesion")));
		
		
		request.getRequestDispatcher("productos.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
