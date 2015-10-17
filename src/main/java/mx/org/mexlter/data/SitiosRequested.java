package mx.org.mexlter.data;

import java.util.Arrays;

public class SitiosRequested {

	private String s1, f1, su, sun, n1, a, ss, valor,sb;
	private String v[];

	public SitiosRequested(String s1, String f1, String su, String sun,
			String n1, String a, String ss, String valor) {
		this.s1 = s1;
		this.f1 = f1;
		this.su = su;
		this.sun = sun;
		this.n1 = n1;
		this.a = a;
		this.ss = ss;
		this.valor = valor;
	}
	
	public SitiosRequested(String s1, String f1, String su, String sun,
			String n1, String a, String ss,String sb, String[] v) {
		this.s1 = s1;
		this.f1 = f1;
		this.su = su;
		this.sun = sun;
		this.n1 = n1;
		this.a = a;
		this.ss = ss;
		this.sb =sb;
		this.v = v;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getF1() {
		return f1;
	}

	public void setF1(String f1) {
		this.f1 = f1;
	}

	public String getSu() {
		return su;
	}

	public void setSu(String su) {
		this.su = su;
	}

	public String getSun() {
		return sun;
	}

	public void setSun(String sun) {
		this.sun = sun;
	}

	public String getN1() {
		return n1;
	}

	public void setN1(String n1) {
		this.n1 = n1;
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String[] getV() {
		return v;
	}

	public void setV(String[] v) {
		this.v = v;
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = sb;
	}

	@Override
	public String toString() {
		return "SitiosRequested [s1=" + s1 + ", f1=" + f1 + ", su=" + su
				+ ", sun=" + sun + ", n1=" + n1 + ", a=" + a + ", ss=" + ss
				+ ", valor=" + valor + ", sb=" + sb + ", v="
				+ Arrays.toString(v) + "]";
	}
	
}