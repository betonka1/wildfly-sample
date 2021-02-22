package explaineverything.wildfly;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EntityService {

	@PersistenceContext
	EntityManager em;
	
	public ExampleEntity create() {
		
		ExampleEntity entity = new ExampleEntity();
		entity.setName("sample name");
		em.persist(entity);
		
		return entity;
	}
	
	public ExampleEntity get(int id) {
		
		return em.find(ExampleEntity.class, id);
	}
}
