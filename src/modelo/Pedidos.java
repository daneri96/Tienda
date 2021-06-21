package modelo;

public class Pedidos {

	private String nombre;
	private int unidades, id;
	private double precio, total;

	public Pedidos(int id,String nombre, int unidades, double precio, double total) {
		this.nombre = nombre;
		this.unidades = unidades;
		this.precio = precio;
		this.total = total;
		this.id = id;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
