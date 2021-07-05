package explaineverything.wildfly.repository;

import explaineverything.wildfly.model.User;

import javax.ejb.Local;

@Local
public interface UserRepository {

    User save(User user);

    User getById(Long id);

    User updateUser(User user);

    void delete(Long id);
}
