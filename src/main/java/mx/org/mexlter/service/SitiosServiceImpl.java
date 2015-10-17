package mx.org.mexlter.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import mx.org.mexlter.data.Network;
import mx.org.mexlter.data.Sitio;
import mx.org.mexlter.data.SitiosRequested;
import mx.org.mexlter.data.Variables;
import mx.org.mexlter.data.json.FormBiome;
import mx.org.mexlter.data.json.FormData;
import mx.org.mexlter.eis.SitiosDao;

@Stateless
public class SitiosServiceImpl implements SitiosService {
	
	@EJB
	private SitiosDao sitioDao;
	
	@Override
	public List<Network> listarTodosLosSitios() {
		return sitioDao.listarTodosLosSitios();
	}

	@Override
	public List<Network> buscarSitiosPorContinente(String continente) {
		return sitioDao.buscarSitiosPorContinente(continente);
	}

	@Override
	public List<SitiosRequested> buscarSitiosConsultados(List<FormData> formData) {
		return sitioDao.buscarSitiosConsultados(formData);
	}

	@Override
	public List<Sitio> buscarSitioPorId(String sitio, String folio) {
		return sitioDao.buscarSitioPorId(sitio, folio);
	}

	@Override
	public List<Variables> listarTodosLasVariables() {
		return sitioDao.listarTodosLasVariables();
	}

	@Override
	public List<Object> datosParaGraficar(List<FormData> formData) {
		return sitioDao.datosParaGraficar(formData);
	}

	@Override
	public List<SitiosRequested> buscarSitiosBiome(FormBiome formBiome) {
		return sitioDao.buscarSitiosBiome(formBiome);
	}

}
