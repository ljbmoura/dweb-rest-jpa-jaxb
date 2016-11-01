package ljbm.rest;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

public final class ContextoSeguranca implements SecurityContext {
	private String nomeUsuario;

	public ContextoSeguranca(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Override
	public boolean isUserInRole(String role) {
		return true;
	}

	@Override
	public boolean isSecure() {
		return false;
	}

	@Override
	public Principal getUserPrincipal() {
		return new Principal() {
			
			@Override
			public String getName() {
				return nomeUsuario;
			}
		};
	}

	@Override
	public String getAuthenticationScheme() {
		return null;
	}
}