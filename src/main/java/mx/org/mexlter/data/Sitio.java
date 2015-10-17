package mx.org.mexlter.data;

import java.util.List;

public class Sitio {
	
	String network,chair,country;
	String name,short_name,cordinator,cordinator_email,web,important_ecosystem,site_type,site_category;
	List<Variables> variable;
	public Sitio(){}
	public Sitio(String network, String chair, String country, String name,
			String short_name, String cordinator, String cordinator_email,
			String web,
			String important_ecosystem, String site_type, String site_category,
			List<Variables> variable) {
		this.network = network;
		this.chair = chair;
		this.country = country;
		this.name = name;
		this.short_name = short_name;
		this.cordinator = cordinator;
		this.cordinator_email = cordinator_email;
		this.web = web;
		this.important_ecosystem = important_ecosystem;
		this.site_type = site_type;
		this.site_category = site_category;
		this.variable = variable;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getChair() {
		return chair;
	}
	public void setChair(String chair) {
		this.chair = chair;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getCordinator() {
		return cordinator;
	}
	public void setCordinator(String cordinator) {
		this.cordinator = cordinator;
	}
	public String getCordinator_email() {
		return cordinator_email;
	}
	public void setCordinator_email(String cordinator_email) {
		this.cordinator_email = cordinator_email;
	}
	public String getImportant_ecosystem() {
		return important_ecosystem;
	}
	public void setImportant_ecosystem(String important_ecosystem) {
		this.important_ecosystem = important_ecosystem;
	}
	public String getSite_type() {
		return site_type;
	}
	public void setSite_type(String site_type) {
		this.site_type = site_type;
	}
	public String getSite_category() {
		return site_category;
	}
	public void setSite_category(String site_category) {
		this.site_category = site_category;
	}
	public List<Variables> getVariable() {
		return variable;
	}
	public void setVariable(List<Variables> variable) {
		this.variable = variable;
	}
	
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	@Override
	public String toString() {
		return "Sitio [network=" + network + ", chair=" + chair + ", country="
				+ country + ", name=" + name + ", short_name=" + short_name
				+ ", cordinator=" + cordinator + ", cordinator_email="
				+ cordinator_email + " web=" + web + ", important_ecosystem="
				+ important_ecosystem + ", site_type=" + site_type
				+ ", site_category=" + site_category + ", variable=" + variable
				+ "]";
	}
	
	
}
