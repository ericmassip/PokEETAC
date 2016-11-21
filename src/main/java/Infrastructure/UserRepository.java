package Infrastructure;

import Entity.User;
import Infrastructure.DAO.DAORepository;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ericmassip on 12/11/16.
 */
public class UserRepository extends DAORepository {

    private Logger log = Logger.getLogger(UserRepository.class);

    public void insertUser(User user) {
        insert(user);
        log.info("User added: " + user.getUsername() + " with id " + user.getId());
    }

    public void selectUser(User user, int primaryKey) {
        select(user, primaryKey);
        log.info("User selected: " + user.getUsername() + " from id " + primaryKey);
    }

    public List<User> getAllUsers() {
        log.info("All Users selected");
        return selectAll(User.class);
    }

    public void updateUser(User user) {
        update(user);
        log.info("User updated: " + user.getUsername());
    }

    public void deleteUser(User user) {
        delete(user);
        log.info("User deleted: " + user.getUsername());
    }
}
