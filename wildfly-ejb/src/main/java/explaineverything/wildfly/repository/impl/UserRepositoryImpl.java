package explaineverything.wildfly.repository.impl;

import explaineverything.wildfly.model.User;
import explaineverything.wildfly.repository.UserRepository;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@Stateless
public class UserRepositoryImpl implements UserRepository {

    private final Logger logger = Logger.getLogger(UserRepositoryImpl.class.getName());

    @PersistenceContext(unitName = "wildfly3PersistenceUnit")
    private EntityManager entityManager;

    @Override
    @Transactional
    public User save(User user) {
        try {
            logger.info("save user ");
            entityManager.persist(user);
            return user;
        } catch (Exception e) {
            logger.info("error during save, e - " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        try {
            return entityManager.find(User.class, id, LockModeType.OPTIMISTIC);
        } catch (Exception e) {
            logger.info("error during get, e - " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        try {
            return entityManager.merge(user);
        } catch (Exception e) {
            logger.info("error during update, e - " + e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            User u = getById(id);
            if (u != null) {
                logger.info("start remove user " + u.getId());
                entityManager.remove(u);
            }
        } catch (Exception e) {
            logger.info("error during delete, e - " + e.getMessage());
        }
    }
}
