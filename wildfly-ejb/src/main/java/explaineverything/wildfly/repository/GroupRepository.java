package explaineverything.wildfly.repository;

import explaineverything.wildfly.model.Group;

import javax.ejb.Local;

@Local
public interface GroupRepository {

    Group save(Group group);

    Group getById(Long id);

    Group updateGroup(Group group);

    void delete(Long id);
}
