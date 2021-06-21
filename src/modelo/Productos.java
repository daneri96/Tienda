package modelo;

public class Productos {
	
	private int id,stock;
	private String nombre,descripcion;
	private double precio;
	private byte[] imagen;

	public Productos(String nombre, double precio,int id,byte[] imagen) {
		this.nombre = nombre;
		this.precio = precio;
		this.id = id;
		this.imagen = imagen;
	}
	
	public Productos(String nombre, double precio,int id) {
		this.nombre = nombre;
		this.precio = precio;
		this.id = id;
	}
	
	

	public Productos(String nombre, double precio, int id, int stock, String descripcion) {
		
		this.nombre = nombre;
		this.precio = precio;
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
