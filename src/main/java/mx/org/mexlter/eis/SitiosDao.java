package mx.org.mexlter.eis;

import java.util.List;

import javax.ejb.Local;

import mx.org.mexlter.data.Network;
import mx.org.mexlter.data.Sitio;
import mx.org.mexlter.data.SitiosRequested;
import mx.org.mexlter.data.Variables;
import mx.org.mexlter.data.json.FormBiome;
import mx.org.mexlter.data.json.FormData;

@Local
public interface SitiosDao {
	
	public List<Network> listarTodosLosSitios();
	
	public List<Variables> listarTodosLasVariables();

	public List<Network> buscarSitiosPorContinente(String continente);
	
	public List<Sitio> buscarSitioPorId(String sitio, String folio);
	
	public List<SitiosRequested> buscarSitiosConsultados(List<FormData> formData);
	
	public List<Object> datosParaGraficar(List<FormData> formData);
	
	public List<SitiosRequested> buscarSitiosBiome(FormBiome formBiome);

}
