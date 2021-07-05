package explaineverything.wildfly.repository.impl;

import explaineverything.wildfly.model.Group;
import explaineverything.wildfly.repository.GroupRepository;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Stateless
public class GroupRepositoryImpl implements GroupRepository {

    private final Logger logger = Logger.getLogger(GroupRepositoryImpl.class.getName());

    @PersistenceContext(unitName = "wildfly3PersistenceUnit")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Group save(Group group) {
        try {
            logger.info("save group");
            entityManager.persist(group);
            return group;
        } catch (Exception e) {
            logger.info("error during save, e - " + e.getMessage());
        }
        return null;
    }

    @Override
    public Group getById(Long id) {
        try {
            return entityManager.find(Group.class, id, LockModeType.OPTIMISTIC);
        } catch (Exception e) {
            logger.info("error during get, e - " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public Group updateGroup(Group group) {
        try {
            entityManager.merge(group);
            return group;
        } catch (Exception e) {
            logger.info("error during update, e - " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            Group g = getById(id);
            if (g != null) {
                logger.info("start remove group " + g.getId());
                entityManager.remove(g);
            }
        } catch (Exception e) {
            logger.info("error during delete, e - " + e.getMessage());
        }
    }
}
