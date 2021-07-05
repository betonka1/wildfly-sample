package explaineverything.wildfly.ejb;

import explaineverything.wildfly.model.User;
import explaineverything.wildfly.repository.UserRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserEJB {

    @Inject
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User get(long id) {
        return userRepository.getById(id);
    }

    public User update(User user) {
        return userRepository.updateUser(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}
