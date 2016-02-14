package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import ljbm.modelo.Modelo;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ModeloEJB {
	private static final Logger LOG = Logger.getLogger(ModeloEJB.class);
 
	@PersistenceContext(unitName = "dweb-rest-jpa-jaxb")
    private EntityManager em;
 
    @SuppressWarnings("unchecked")
	public List<Modelo> findAll() {
    	LOG.debug("buscando todos");
    	Query query = em.createQuery("select m FROM Modelo m");
    	return query.getResultList();
    }

    public Modelo findById(Long id) {
        return em.find(Modelo.class, id);
    }
 
    public Modelo insert(Modelo entity) {
        em.persist(entity);
        return entity;
    }
    
    public void update(Modelo entity) {
        em.merge(entity);
    }
 
    public void delete(Modelo deletableEntity) {
        em.remove(em.merge(deletableEntity));
    }
}