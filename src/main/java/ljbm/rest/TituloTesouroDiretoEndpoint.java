package ljbm.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;
//import org.glassfish.jersey.client.filter.HttpDigestAuthFilter;

import ljbm.ejb.TituloTesouroDiretoEJB;
import ljbm.modelo.TituloTesouroDireto;

@Stateless
@Path(value = "/titulosTD")
public class TituloTesouroDiretoEndpoint {

	@Inject
	private Logger log;

	@EJB
	private TituloTesouroDiretoEJB eJBService;

	@GET
	@Path(value = "/")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response lista() {
		List<TituloTesouroDireto> res = eJBService.findAll();
		GenericEntity<List<TituloTesouroDireto>> envolucro = new GenericEntity<List<TituloTesouroDireto>>(res) {
		};
		log.trace("GET rest/titulosTD/");
		return Response.ok(envolucro).build();
	}

	@GET
	@Path("/{numero:[0-9][0-9]*}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findById(@PathParam("numero") Long id) {
		log.trace("GET rest/titulosTD/" + id.toString());
		TituloTesouroDireto entity = eJBService.findById(id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(final TituloTesouroDireto entity) {
		log.trace("POST rest/titulosTD/");
		TituloTesouroDireto modeloInserido = eJBService.insert(entity);

		return Response.ok(modeloInserido).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		log.trace("DELETE rest/titulosTD/" + id.toString());
		TituloTesouroDireto deletableEntity = eJBService.findById(id);
		if (deletableEntity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		eJBService.delete(deletableEntity);
		return Response.noContent().build();
	}
}
