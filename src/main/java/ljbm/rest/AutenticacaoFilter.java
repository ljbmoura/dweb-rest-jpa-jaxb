package ljbm.rest;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

//import org.apache.logging.log4j.Logger;
import org.apache.log4j.Logger;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AutenticacaoFilter implements ContainerRequestFilter {

//	@Inject Logger log;
	private static final Logger log = Logger.getLogger(AutenticacaoFilter.class);
	
	
	@Context
    private HttpServletRequest httpRequest;
	
	@Override
	public void filter(final ContainerRequestContext requestContext) {
		MultivaluedMap<String, String> headers = requestContext.getHeaders();
		
		String ipCliente = httpRequest.getRemoteAddr();
		log.trace("IP chamador: " + ipCliente);
		if("0:0:0:0:0:0:0:1".equals(ipCliente)) {
			ipCliente = "127.0.0.1";
		}
		log.trace("Host chamador: " + httpRequest.getServerName());
		log.trace("Cabeçalhos da requisição: ");;
		for (Entry<String, List<String>> e : headers.entrySet()) {
			log.trace(e.getKey() + ": " + e.getValue());
		}
		log.trace("Cookies da requisição: ");
		Map<String, Cookie> cookies = requestContext.getCookies();
		for (Entry<String, Cookie> e : cookies.entrySet()) {
			log.trace("\t" + e.getKey() + ": " + e.getValue());
		}
//		requestContext.getUriInfo().getQueryParameters().getFirst("Sistema");
		SecurityContext securityContext = new ContextoSeguranca(requestContext.getCookies().get("JSESSIONID").getValue());
		requestContext.setSecurityContext(securityContext);
		
	}



}
