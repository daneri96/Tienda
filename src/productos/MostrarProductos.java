package productos;

import java.io.IOException;
import java.io.OutputStream;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.ModeloCarrito;
import modelo.ModeloProductos;
import modelo.ModeloUsuarios;
import modelo.ProductoCarrito;
import modelo.Usuarios;


@WebServlet("/MostrarProductos")
public class MostrarProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;
    int pag = 0;
    byte [] buf = null;

	String phototype = "jpeg";
	
    public MostrarProductos() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		ModeloProductos md = new ModeloProductos();
		ModeloCarrito mod = new ModeloCarrito();
		request.setAttribute("MostrarCarrito", false);
		Usuarios aux = (Usuarios)request.getSession().getAttribute("sesion");
		
		if (aux.getNombre().equals("anonimo")) {
			ProductoCarrito[] p =(ProductoCarrito[]) request.getSession().getAttribute("Carrito");
			request.setAttribute("ProductosCarrito", p);
		}
		else request.setAttribute("ProductosCarrito", mod.mostrarEnCarrito(aux));
			
			
		
		
		
		
		
		
		request.getSession().setAttribute("categoria_listada", 1);
		
			
		request.setAttribute("Productos", md.paginarRopa((Usuarios)request.getSession().getAttribute("sesion"),(int)request.getSession().getAttribute("categoria_listada"),pag,4));
		
		
	
		
		
		
		request.getRequestDispatcher("catalogo.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String control = request.getParameter("pagename");
		ModeloProductos md = new ModeloProductos();
		ModeloCarrito mod = new ModeloCarrito();
		request.setAttribute("MostrarCarrito", false);
		Usuarios aux = (Usuarios)request.getSession().getAttribute("sesion");
		HttpSession sesion;
		sesion = request.getSession();
		
		
		switch(control)
		
		{
			case "Siguiente":
					//paginacion de productos de 4 en 4 realizamos el control de página
				
					if(pag < md.numeroPaginas((Usuarios)request.getSession().getAttribute("sesion"),4)) 
					{
						pag++;

					}
				
					break;
			case "Atras":
				
				if(pag > 0) 
				{
					pag--;
				}
				
				break;
				
		case "Manga Corta":
				request.getSession().setAttribute("categoria_listada", 1);

				break;
		case "Tirantes":
				request.getSession().setAttribute("categoria_listada", 2);
		
				break;
		case "Manga Larga":
				request.getSession().setAttribute("categoria_listada", 3);
		
				break;
		case "Sudaderas":
				request.getSession().setAttribute("categoria_listada", 4);
				
				break;
		case "Add Carrito":
					int codigo =Integer.parseInt(request.getParameter("codigo_id"));
					String nombre =request.getParameter("nombre");
					float precio =Float.parseFloat(request.getParameter("precio"));
					ProductoCarrito[] p1,p;
					
					if (aux.getNombre().equals("anonimo")) 
					{
						p = (ProductoCarrito[]) request.getSession().getAttribute("Carrito");	
						
						int pos = -1,i=0;
						
						while(p != null && i<p.length && pos == -1) 
						{
							if(p[i].getId() == codigo) pos = i;					
							i++;
						}
						
						int nuevolength=1;
						
						if(p!=null) nuevolength = p.length + 1;
						 
						if(pos == -1) 
						{
							p1 = new ProductoCarrito[nuevolength];
							i = 0;
							
							if(p!=null)
								for(ProductoCarrito pc: p) p1[i++] = pc;
							
							
							p1[nuevolength-1] = new ProductoCarrito(nombre, precio, codigo , 1);
						
							request.getSession().setAttribute("Carrito", p1);
							
						}
						else 
						{
							p[pos].setCantidad(p[pos].getCantidad() + 1);
							request.getSession().setAttribute("Carrito", p);
						}
					
						
					} else 
						mod.insertarCarrito((Usuarios)request.getSession().getAttribute("sesion"),codigo);					
		break;	
		
		case "-":
			int codigo_id =Integer.parseInt(request.getParameter("id_producto"));
			
			
			if (aux.getNombre().equals("anonimo")) 
			{
				ProductoCarrito[] p2 =(ProductoCarrito[]) request.getSession().getAttribute("Carrito");
				int pos = -1,i=0;
				
				while(i<p2.length && pos == -1) 
				{
					if(p2[i].getId() == codigo_id) pos = i;					
					i++;
				}
				
				int cantidad_actual = p2[pos].getCantidad();
				
				if(cantidad_actual == 1)
				{
					ProductoCarrito[] p3 = new ProductoCarrito[p2.length-1];
					for(int k=0; k<pos;k++) p3[k] = p2[k];
					for(int k=pos+1; k<p2.length;k++) p3[k-1] = p2[k];
					p2 = p3;
				}
				else p2[pos].setCantidad(cantidad_actual-1);
				
				request.getSession().setAttribute("Carrito", p2);
			} else
				mod.eliminarCarrito((Usuarios)request.getSession().getAttribute("sesion"),codigo_id);
			
			request.setAttribute("MostrarCarrito", true);
		break;
			
		case "+":
				codigo_id =Integer.parseInt(request.getParameter("id_producto"));
				
				if (aux.getNombre().equals("anonimo")) 
				{
					ProductoCarrito[] p2 =(ProductoCarrito[]) request.getSession().getAttribute("Carrito");
					int pos = -1,i=0;
					
					while(i<p2.length && pos == -1) 
					{
						if(p2[i].getId() == codigo_id) pos = i;					
						i++;
					}
					
					p2[pos].setCantidad(p2[pos].getCantidad() + 1);
					request.getSession().setAttribute("Carrito", p2);
				} else	
					mod.insertarCarrito((Usuarios)request.getSession().getAttribute("sesion"),codigo_id);
				
				request.setAttribute("MostrarCarrito", true);
			break;
		case "Aceptar Compra":
			if(mod.procesarCarrito((Usuarios)request.getSession().getAttribute("sesion")))
			{
				sesion.setAttribute("comprado","Su compra se ha realizado con exito");
				
			}else 
			{
				sesion.setAttribute("no_comprado", "Su compra no pudo ser tramitada");
				
			}
			break;
			
		case "Cerrar Carrito":
				sesion.setAttribute("comprado", null);
			break;
		}
		
		
		
		
		if(!aux.getNombre().equals("anonimo"))
			request.setAttribute("ProductosCarrito", mod.mostrarEnCarrito((Usuarios)request.getSession().getAttribute("sesion")));
		else {} //anónimo, no hay que hacer nada ya que está todo en el sesion
	

		request.setAttribute("Productos",md.paginarRopa((Usuarios)request.getSession().getAttribute("sesion"),(int)request.getSession().getAttribute("categoria_listada"),pag,4));
		
	
		request.getRequestDispatcher("catalogo.jsp").forward(request, response);
		
	}
	
		
}
	





