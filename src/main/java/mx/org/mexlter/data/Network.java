package mx.org.mexlter.data;

public class Network {
	private Integer id;
	private String sitio;
	private String nombre;

	public Network(Integer id, String sitio, String nombre) {
		this.id = id;
		this.sitio = sitio;
		this.nombre = nombre;
	}

	public String getSitio() {
		return sitio;
	}

	public void setSitio(String sitio) {
		this.sitio = sitio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Sitios [id=" + id + ", sitio=" + sitio + ", nombre=" + nombre
				+ "]";
	}

}
