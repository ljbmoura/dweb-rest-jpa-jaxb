//package ljbm.a_refatorar;
//
//import java.util.List;
//
//import javax.ejb.Stateless;
//import javax.ejb.TransactionManagement;
//import javax.ejb.TransactionManagementType;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.Query;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.GenericEntity;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//
//@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER)
//@Path(value = "/modelos")
//public class Servico {
//
//	@Inject
//	@RequestScoped
//	private EntityManager em;
//
//	@GET
//	@Path(value = "/disponivel")
//	@Produces(MediaType.APPLICATION_JSON)
//	public String getMessage() {
//		return "Sim";
//	}
//
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	@Path(value = "/")
//	public Response lista() {
//
//		Query query = em.createQuery("select m FROM Modelo m");
//		@SuppressWarnings("unchecked")
//		List<Modelo> list = query.getResultList();
//		GenericEntity<List<Modelo>> bookWrapper = new GenericEntity<List<Modelo>>(list) {
//		};
//		return Response.ok(bookWrapper).build();
//	}
//
//	@GET
//	@Path("/{numero}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Modelo getModelo(@PathParam("numero") String numero) {
//		Query query = em.createQuery("select m FROM Modelo m where m.numero=:numero");
//		query.setParameter("numero", Integer.parseInt(numero));
//
//		@SuppressWarnings("unchecked")
//		List<Modelo> res = query.getResultList();
//
//		return res.isEmpty() ? Modelo.vazio() : (Modelo) res.get(0);
//	}
//
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response addModelo(final Modelo modelo) {
//		
//		em.persist(modelo);
//		
//		return Response.ok(modelo).build();
//	}
//
//	// @PUT
//	// @Path("/{numero}")
//	// public Modelo updateModelo(@PathParam("numero") String id, String title)
//	// {
//	// }
//	//
//	// @DELETE
//	// @Path("/{numero}")
//	// public Modelo removeModelo(@PathParam("numero") String id) {
//	// }
//	
//	
//}
