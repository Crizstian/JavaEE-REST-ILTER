package mx.org.mexlter.data.json;

public class FormData {

	private Integer x;
	private String tipoX;
	private String signoX;
	private Integer valorX;
	private String sql;

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public String getTipoX() {
		return tipoX;
	}

	public void setTipoX(String tipoX) {
		this.tipoX = tipoX;
	}

	public String getSignoX() {
		return signoX;
	}

	public void setSignoX(String signoX) {
		this.signoX = signoX;
	}

	public Integer getValorX() {
		return valorX;
	}

	public void setValorX(Integer valorX) {
		this.valorX = valorX;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {
		return "FormData [x=" + x + ", tipoX=" + tipoX + ", signoX=" + signoX
				+ ", valorX=" + valorX + ", sql=" + sql + "]";
	}

}
