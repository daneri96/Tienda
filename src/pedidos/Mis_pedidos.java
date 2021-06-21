package pedidos;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.ModeloPedidos;
import modelo.Usuarios;


@WebServlet("/Mis_pedidos")
public class Mis_pedidos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Mis_pedidos() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModeloPedidos md = new ModeloPedidos();
		
		request.setAttribute("Mis_pedidos", md.mostrarPedidos((Usuarios)request.getSession().getAttribute("sesion")));
		
		request.getRequestDispatcher("mis_pedidos.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
