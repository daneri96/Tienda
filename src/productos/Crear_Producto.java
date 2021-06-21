package productos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ModeloCategorias;
import modelo.Usuarios;


@WebServlet("/Crear_Producto")
public class Crear_Producto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Crear_Producto() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloCategorias md = new ModeloCategorias();
		
		
		
		
	    request.setAttribute("Categorias", md.mostrarCategorias((Usuarios)request.getSession().getAttribute("sesion")));
		request.getRequestDispatcher("crear_producto.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
