package mx.org.mexlter.wrapper;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import mx.org.mexlter.data.Network;
import mx.org.mexlter.data.Sitio;
import mx.org.mexlter.data.SitiosRequested;
import mx.org.mexlter.data.Variables;

@XmlRootElement
public class SitiosWrapper implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private List<Network> list;
	@XmlElement
	private List<SitiosRequested> list2;
	@XmlElement
	private List<Variables> list3;
	@XmlElement
	private List<Sitio> list4;
	@XmlElement
	private List<Object> list5;
	
	public List<Network> getList() {
		return list;
	}
	public void setList(List<Network> list) {
		this.list = list;
	}
	public List<SitiosRequested> getList2() {
		return list2;
	}
	public void setList2(List<SitiosRequested> list2) {
		this.list2 = list2;
	}
	public List<Variables> getList3() {
		return list3;
	}
	public void setList3(List<Variables> list3) {
		this.list3 = list3;
	}
	public List<Sitio> getList4() {
		return list4;
	}
	public void setList4(List<Sitio> list4) {
		this.list4 = list4;
	}
	public List<Object> getList5() {
		return list5;
	}
	public void setList5(List<Object> list5) {
		this.list5 = list5;
	}
	@Override
	public String toString() {
		return "SitiosWrapper [list=" + list + ", list2=" + list2 + ", list3="
				+ list3 + ", list4=" + list4 + ", list5=" + list5 + "]";
	}
	
}
