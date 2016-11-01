package ljbm.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import org.apache.logging.log4j.Logger;
import org.apache.log4j.Logger;

import ljbm.modelo.TituloTesouroDireto;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TituloTesouroDiretoEJB {
	private static final Logger log = Logger.getLogger(TituloTesouroDiretoEJB.class);
	
//	@Inject Logger log;
 
	@PersistenceContext(unitName = "dweb-rest-jpa-jaxb")
    private EntityManager em;
 
    @SuppressWarnings("unchecked")
	public List<TituloTesouroDireto> findAll() {
    	log.trace("buscando todos os titulos TD");
    	Query query = em.createQuery("select ttd FROM TituloTesouroDireto ttd");
    	return query.getResultList();
    }

    public TituloTesouroDireto findById(Long id) {
        return em.find(TituloTesouroDireto.class, id);
    }
 
    public TituloTesouroDireto insert(TituloTesouroDireto entity) {
    	log.trace("inserindo titulo TD " + entity.toString());
        em.persist(entity);
        return entity;
    }
    
    public void update(TituloTesouroDireto entity) {
        em.merge(entity);
    }
 
    public void delete(TituloTesouroDireto deletableEntity) {
        em.remove(em.merge(deletableEntity));
    }
}