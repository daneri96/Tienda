package modelo;



public class Usuarios {

	private int id;
	private String nombre,apellidos,email,password,direccion,rol;
	private Conexion conn;
	
	
	public Usuarios(int id,String nombre,String apellidos,String email,String password,String direccion,String rol) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
		this.direccion = direccion;
		this.rol = rol;
		conn = new Conexion("ns3034756.ip-91-121-81.eu:5432","a20-dalvcor","a20-dalvcor","a20-dalvcor");
	}	
	

	public Usuarios(String nombre) {
		this.nombre = nombre;
		conn = new Conexion("ns3034756.ip-91-121-81.eu:5432","a20-dalvcor","a20-dalvcor","a20-dalvcor");

	}


	public Conexion getConn() {
		return conn;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
