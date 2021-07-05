package explaineverything.wildfly.ejb;

import explaineverything.wildfly.model.Group;
import explaineverything.wildfly.repository.GroupRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class GroupEJB {

    @Inject
    private GroupRepository groupRepository;

    public Group create(Group group) {
        return groupRepository.save(group);
    }

    public Group get(long id) {
        return groupRepository.getById(id);
    }

    public Group update(Group group) {
        return groupRepository.updateGroup(group);
    }

    public void delete(Long id) {
        groupRepository.delete(id);
    }
}
