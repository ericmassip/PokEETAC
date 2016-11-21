package Business;

import Entity.User;
import Infrastructure.UserRepository;

/**
 * Created by ericmassip on 12/11/16.
 */
public class UserBusiness {
    UserRepository userRepository = new UserRepository();

    public boolean isRegistered(String username, String password) {
        boolean isRegistered = false;
        for (User user: userRepository.getAllUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                isRegistered = true;
            }
        }
        return isRegistered;
    }
}
