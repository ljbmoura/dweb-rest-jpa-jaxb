package ljbm.rest;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import ejb.ModeloEJB;
import ljbm.modelo.Modelo;

@Stateless
@Path(value = "/modelos")
public class ModeloEndpoint {

	@EJB
	private ModeloEJB modeloEJBService;

	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path(value = "/")
	public Response lista() {

		List<Modelo> lista = modeloEJBService.findAll();
		GenericEntity<List<Modelo>> bookWrapper = 
				new GenericEntity<List<Modelo>>(lista) {};
		return Response.ok(bookWrapper).build();
	}



	@GET
	@Path("/{numero:[0-9][0-9]*}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response findById(@PathParam("numero") Long numero) {
		Modelo entity = modeloEJBService.findById(numero);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insert(final Modelo entity) {
		
		Modelo modeloInserido = modeloEJBService.insert(entity);
		
		return Response.ok(modeloInserido).build();
	}	

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Modelo entity) {
		modeloEJBService.update(entity);
		return Response.noContent().build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Modelo deletableEntity = modeloEJBService.findById(id);
		if (deletableEntity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		modeloEJBService.delete(deletableEntity);
		return Response.noContent().build();
	}
}
