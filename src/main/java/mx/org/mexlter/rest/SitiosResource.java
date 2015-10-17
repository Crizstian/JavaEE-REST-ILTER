package mx.org.mexlter.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mx.org.mexlter.data.json.FormBiome;
import mx.org.mexlter.data.json.FormData;
import mx.org.mexlter.service.SitiosService;
import mx.org.mexlter.wrapper.SitiosWrapper;

/**
 * REST Service to expose the data to ILTER web app.
 * @author Cristian Ramirez
 */

@Stateless
@ApplicationPath("/resources")
@Path("/sitios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SitiosResource extends Application {

	@EJB
	private SitiosService sitioService;

	private SitiosWrapper sitiosListed(SitiosWrapper wrapper, String continente) {
		wrapper.setList(sitioService.buscarSitiosPorContinente(continente));
		return wrapper;
	}

	private SitiosWrapper specificSite(SitiosWrapper wrapper, String sitio,
			String folio) {
		wrapper.setList4(sitioService.buscarSitioPorId(sitio, folio));
		return wrapper;
	}

	private SitiosWrapper consultaDao(SitiosWrapper wrapper, int id) {
		switch (id) {
		case 1:
			wrapper.setList(sitioService.listarTodosLosSitios());
			break;
		case 2:
			wrapper.setList3(sitioService.listarTodosLasVariables());
			break;
		}
		return wrapper;
	}

	private SitiosWrapper biomeListed(SitiosWrapper wrapper, FormBiome formBiome) {
		wrapper.setList2(sitioService.buscarSitiosBiome(formBiome));
		return wrapper;
	}

	private SitiosWrapper consultaDao(SitiosWrapper wrapper,
			List<FormData> formData, int id) {
		switch (id) {
		case 1:
			wrapper.setList2(sitioService.buscarSitiosConsultados(formData));
			break;
		case 2:
			wrapper.setList5(sitioService.datosParaGraficar(formData));
			break;
		}
		return wrapper;
	}

	private Response responder(Object obj) {
		return Response.ok().entity(obj).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listSitios() {
		return responder(consultaDao(new SitiosWrapper(), 1));
	}

	@GET
	@Path("{continente}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listSpecific(@PathParam("continente") String continente) {
		return responder(sitiosListed(new SitiosWrapper(), continente));
	}

	@GET
	@Path("/variables")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listVariables() {
		return responder(consultaDao(new SitiosWrapper(), 2));
	}

	@GET
	@Path("/{sitio}/{folio}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultaSitio(@PathParam("sitio") String sitio,
			@PathParam("folio") String folio) {
		return responder(specificSite(new SitiosWrapper(), sitio, folio));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarSitios(List<FormData> formData) {
		return responder(consultaDao(new SitiosWrapper(), formData, 1));
	}

	@POST
	@Path("/grafica")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response grafica(List<FormData> formData) {
		return responder(consultaDao(new SitiosWrapper(), formData, 2));
	}

	@POST
	@Path("/biome")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response biome(FormBiome formBiome) {
		return responder(biomeListed(new SitiosWrapper(), formBiome));
	}

}
