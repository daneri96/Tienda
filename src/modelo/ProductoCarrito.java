package modelo;

public class ProductoCarrito extends Productos{
	
	private int cantidad;
	
	
	public ProductoCarrito(String nombre,double precio , int id, int cantidad) {
		super(nombre, precio, id);
		this.cantidad = cantidad;
		
	}
	

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	


}
