package mx.org.mexlter.data;

public class Variables {
	
	private Integer id;
	private String nombre, unidad,value,type;
	
	public Variables(Integer id, String nombre, String unidad) {
		this.id  = id;
		this.nombre = nombre;
		this.unidad = unidad;
	}
	
	public Variables(String value, String type, Integer id) {
		this.id = id;
		this.value = value;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Variables [id=" + id + ", nombre=" + nombre + ", unidad="
				+ unidad + ", value=" + value + "]";
	}

	

}
