package ljbm.rest;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.Logger;


@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class CrossOriginResourceSharingFilter implements ContainerResponseFilter {

	@Inject
	private Logger log;
	
	@Context
	private HttpServletRequest httpRequest;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext cres) throws IOException {

		String origin = httpRequest.getHeader("Origin");
		log.trace("Origin: " + origin);

		cres.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:3000");
		// cres.getHeaders().add("Access-Control-Allow-Origin", "*");
		cres.getHeaders().add("Access-Control-Allow-Credentials", true);
		cres.getHeaders().add("Access-Control-Allow-Headers",
				"Origin, Content-Type, Accept, Authorization, X-Requested-With, My-Header");
		cres.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		cres.getHeaders().add("Access-Control-Max-Age", "20000");
	}
}