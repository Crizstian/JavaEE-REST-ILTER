package mx.org.mexlter.rest;

import javax.ws.rs.core.Response;

public interface RestTools  {
	
	default Response responder(Object obj) {
		return Response.ok() // 200
				.entity(obj)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.allow("OPTIONS").build();
	}
	
	
}