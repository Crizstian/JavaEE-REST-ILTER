package mx.org.mexlter.data.json;

public class FormBiome {

	private String sql;
	private Boolean check1,check2,check3;

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Boolean getCheck1() {
		return check1;
	}

	public void setCheck1(Boolean check1) {
		this.check1 = check1;
	}

	public Boolean getCheck2() {
		return check2;
	}

	public void setCheck2(Boolean check2) {
		this.check2 = check2;
	}

	public Boolean getCheck3() {
		return check3;
	}

	public void setCheck3(Boolean check3) {
		this.check3 = check3;
	}

	@Override
	public String toString() {
		return "formBiome [sql=" + sql + ", check1=" + check1 + ", check2="
				+ check2 + ", check3=" + check3 + "]";
	}
	
	

}
