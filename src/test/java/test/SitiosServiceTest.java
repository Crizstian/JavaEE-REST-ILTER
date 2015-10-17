package test;

import java.util.List;

import mx.org.mexlter.data.Network;
import mx.org.mexlter.data.Sitio;
import mx.org.mexlter.data.Variables;
import mx.org.mexlter.eis.SitiosDaoImpl;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class SitiosServiceTest {

	// @Test
	public void testAllNetworks() {

		List<Network> sitios = new SitiosDaoImpl().listarTodosLosSitios();

		for (Network sitio : sitios) {
			System.out.println(sitio);
		}
		System.out.println("Fin test EJB PersonaService");
	}

	// @Test
	public void testNetworkById() {

		List<Network> sitios = new SitiosDaoImpl()
				.buscarSitiosPorContinente("Europa");

		for (Network sitio : sitios) {
			System.out.println(sitio);
		}
		System.out.println("Fin test EJB PersonaService");
	}

	//@Test
	public void variables() {

		List<Variables> v = new SitiosDaoImpl().listarTodosLasVariables();

		for (Variables v1 : v) {
			System.out.println(v1);
		}
		System.out.println("Fin test EJB PersonaService");
	}

	// @Test
	public void pruebaPOST() {
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/ILTER/resources/json/metallica/post");

			JSONObject input2 = new JSONObject();
			input2.put("singer", "Metallica");
			input2.put("title", "Fade To Black");
			JSONObject input3 = new JSONObject();
			input3.put("singer", "WW");
			input3.put("title", "w");
			JSONArray input = new JSONArray();
			input.put(input2);
			input.put(input3);

			System.out.println(input.toString());

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	// @Test
	public void testPostCollection() {
		try {

			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/ILTER/resources/sitios");

			JSONObject input2 = new JSONObject();
			input2.put("x", 13);
			input2.put("tipoX", "Average");
			input2.put("signoX", ">");
			input2.put("valorX", 30);
			input2.put("sql", "0");
			JSONObject input4 = new JSONObject();
			input4.put("x", 0);
			input4.put("tipoX", "0");
			input4.put("signoX", "0");
			input4.put("valorX", 0);
			input4.put("sql", " and (d.IdRed='MexLTER' or d.IdRed='US LTER' )");

			JSONArray input = new JSONArray();
			input.put(input2);
			input.put(input4);

			System.out.println(input);

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void fichaTecnica() {
		List<Sitio> sitio = new SitiosDaoImpl()
				.buscarSitioPorId("MexLTER", "1");

		for (Sitio s : sitio) {
			System.out.println(s);
		}
		System.out.println("Fin test EJB PersonaService");
	}

}