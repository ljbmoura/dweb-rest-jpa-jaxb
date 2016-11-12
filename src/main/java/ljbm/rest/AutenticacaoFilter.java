package ljbm.rest;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.Logger;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AutenticacaoFilter implements ContainerRequestFilter {

	@Inject
	private Logger log;

	@Context
	private HttpServletRequest httpRequest;

	@Override
	public void filter(final ContainerRequestContext requestContext) {
		MultivaluedMap<String, String> headers = requestContext.getHeaders();

		String ipCliente = httpRequest.getRemoteAddr();
		log.trace("IP chamador: " + ipCliente);
		if ("0:0:0:0:0:0:0:1".equals(ipCliente)) {
			ipCliente = "127.0.0.1";
		}
		if (log.isTraceEnabled()) {
			log.trace("Host chamador: " + httpRequest.getServerName());
			log.trace("Cabeçalhos da requisição: ");
			for (Entry<String, List<String>> e : headers.entrySet()) {
				log.trace("\t" + e.getKey() + ": " + e.getValue());
			}
		}
		
		Map<String, Cookie> cookies = requestContext.getCookies();
		if (cookies == null || cookies.isEmpty()) {
			throw new BadRequestException("Credenciais de usuário não foram enviadas.");
		}
		if (log.isTraceEnabled()) {
			log.trace("Cookies da requisição: ");
			for (Entry<String, Cookie> e : cookies.entrySet()) {
				log.trace("\t" + e.getKey() + ": " + e.getValue());
			}
		}
		javax.ws.rs.core.Cookie cookie = cookies.get("chaveLJBM");
		SecurityContext securityContext = null;
		if (cookie != null) {
			String ck = cookie.getValue();
			securityContext = new ContextoSeguranca(ck);
		}
		requestContext.setSecurityContext(securityContext);

	}

}
